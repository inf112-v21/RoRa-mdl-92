package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.source.tree.BinaryTree;


public class Game implements ApplicationListener {
    private SpriteBatch batch;
    public int turn=0;
    public List<Player> playerList = new ArrayList<>();
    public List<Card> cards = new ArrayList<Card>();
    public List <Card> discard = new ArrayList<Card>();
    Map<Player, List<Integer>> playerRegisters = new HashMap<>();
    public TileMap gameBoard;
    public Map<Integer, Map<Player, Integer>> registerHistory = new HashMap<>();
    public Random rand = new Random();
    public int currentUser = 0; // the player that the applications user controls
    public boolean isOnline = false;
    public NetworkComponent networkComponent = null;
    public Flag flag = null;


    public void DoTurn() {
        turn += 1;
        if(isOnline){
            //get inputs from other players
            ArrayList<playerInputs> otherPlayerInputs = networkComponent.communicateToPlayers(playerList.get(currentUser).cardInputs);
            for(int i = 0; i < playerList.size(); i++){ // set inputs to respective players
                if(i != currentUser){
                    playerList.get(i).cardInputs = otherPlayerInputs.get(0);
                    otherPlayerInputs.remove(0);
                }
            }
        }
        else { // if you only play with IA
            for(int i = 0; i < playerList.size(); i++){
                if(i != currentUser){
                    playerList.get(i).doAiTurn();
                }
            }
        }



        //Deal Cards to players.
        for(Player p: playerList){
            //Player.takeCards(this);
            while(p.canPlayCard()){
                p.playCard();
            }
        }
        //checks if a robot is on the flag
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).playerRobot.posY == flag.posY && playerList.get(i).playerRobot.posX == flag.posX){
                System.out.println("Player " +String.valueOf(i+1) + " WINS!");
            }
        }

        //Each game round consists of 5 cycles of actions, one for each programmed program card.
        for(int phase = 0; phase < 5; phase++) {

            //HandleProgram(Board.getRobots(), phase);    //dependant on getting robots from Board.
            HandleProgram(new ArrayList<Robot>(), phase); //Substitute for lone above.

        }
    }

    public Card DealCard(){
        if(cards.size() == 0){
            List<Card> temp = cards;
            cards = discard;
            discard = temp;
        }
        Card out;
        int i = rand.nextInt(cards.size()-1);
        out = cards.get(i);
        cards.remove(i);
        return null;
    }

    // This function takes a list of robots and a phase, and executes robots action for that phase in descending order or priority
    private void HandleProgram(List<Robot> allRobots, int _currentPhase){
        Collections.sort(allRobots, (d1, d2) -> {
                    return d1.GetCard(_currentPhase).priority - d2.GetCard(_currentPhase).priority;
                });
        for(int i = 0; i < allRobots.size(); i++){
            if((allRobots.get(i).isPowered)){       //dependant on knowing whether the robot is powered.
                allRobots.get(_currentPhase).DoAction(_currentPhase); //dependant on function from Robot
            }
        }
    }

    public void CompleteRegisters() {
        for (int i=0; i<5; i++) {
            Map<Player, Integer> playerRegister = new HashMap<>();
            for (Player p : playerList) {
                playerRegister.put(p, playerRegisters.get(p).get(i));
            }
            registerHistory.put(i+1, playerRegister);
        }
    }

    public String ShowPlayers() {
        String output = "";
        for (Player p : playerList) {
            output += "Player " + (playerList.indexOf(p)+1) + ": " + p.cardsList.toString() + '\n';
        }

        return output;
    }



    // overrides from application listener
    @Override
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("select mode, 1 for singel player, 2 for multiplayer");
        if(scanner.nextInt() == 2){
            isOnline = true;
        }
        if(isOnline){
            System.out.println("press 1 for host, 2 for client");
            if(scanner.nextInt() == 1){
                System.out.println("enter Port");
                scanner.nextLine();
                networkComponent = new Host(scanner.nextInt());
            }
            else {
                System.out.println("enter host IP");
                scanner.nextLine();
                String ip = scanner.nextLine();
                System.out.println("enter host Port");
                scanner.nextLine();
                networkComponent = new Client(ip,scanner.nextInt());
                Client c = (Client)networkComponent;
                currentUser = c.getPlayerNr();
            }
        }
        batch = new SpriteBatch();
        InputReader inputReader = new InputReader();
        gameBoard = new TileMap();
        flag = new Flag(5,5);
        flag.texture =  new Texture(Gdx.files.internal("src/assets/FlagTiltSolid_0.png"));
        playerList.add(new Player(new Robot(0,0)));
        playerList.add(new Player(new Robot(11,11)));
        // add dummy cards to players hand
        for(Player p : playerList){
            p.hand.add(new Move1Card());
            p.hand.add(new UTurnCard());
            p.hand.add(new TurnRightCard());
            p.hand.add(new Move3Card());
            p.hand.add(new TurnRightCard());
            p.hand.add(new Move3Card());
            p.hand.add(new Move3Card());
            p.hand.add(new Move3Card());
            p.hand.add(new Move3Card());
        }

        Gdx.input.setInputProcessor(inputReader);

    }

    @Override
    public void dispose() {
        gameBoard.dispose();
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        gameBoard.render();
        batch.begin();
        playerList.get(currentUser).drawHand(batch);
        for(Player p :playerList){
            p.playerRobot.draw(batch);
        }
        batch.draw(flag.texture,flag.posX*83,flag.posY*83);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }


    // a class to read inputs from the application user
    class InputReader implements InputProcessor{

        @Override
        public boolean keyDown(int i) {
            if(i == 44){
                DoTurn();
            }
            //checking if robot is on flag, this shouldn't be here
//        for(int z = 0; z < Board.allFlags.length; z++){
//            if ((playerRobot.posX == Board.allFlags[z].posX) && (playerRobot.posY == Board.allFlags[z].posY)){
//                Board.allFlags[z].visitedP1 = true;
//            }
//        }
//        if (Board.allFlags[0].visitedP1 && Board.allFlags[1].visitedP1 && Board.allFlags[2].visitedP1){
//            System.out.println("Yay, You Won");
//        }
            return true;
        }

        @Override
        public boolean keyUp(int i) {
            return false;
        }

        @Override
        public boolean keyTyped(char c) {
            return false;
        }

        @Override
        public boolean touchDown(int i, int i1, int i2, int i3) {
            return false;
        }

        @Override
        public boolean touchUp(int i, int i1, int i2, int i3) {
            playerList.get(currentUser).touchUp();
            return true;
        }

        @Override
        public boolean touchDragged(int i, int i1, int i2) {
            return false;
        }

        @Override
        public boolean mouseMoved(int i, int i1) {
            return false;
        }

        @Override
        public boolean scrolled(int i) {
            return false;
        }
    }
}
