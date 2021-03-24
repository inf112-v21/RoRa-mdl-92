package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
    public List<Card> discard = new ArrayList<Card>();
    public List<Card> cardLibrary = new ArrayList<Card>();
    public TileMap gameBoard;
    public Random rand = new Random();
    public int currentUser = 0; // the player that the applications user controls
    public boolean isOnline = false;
    public NetworkComponent networkComponent = null;
    public Flag flag = null;
    public Board board = new Board();


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
                    playerList.get(i).doAiTurn(rand);
                }
            }
        }


        /* Testing HandleProgram
        //Deal Cards to players.
        for(Player p: playerList){
            //Player.takeCards(this);
            while(p.canPlayCard()){
                p.playCard();
            }
        }
         */

        for(int i = 0; i < 5; i++){
            HandleProgram(i);
            board.ExpressBeltMove();
            board.BeltMove();
            board.PusherMove();
            board.WheelRotate();
            board.HoleFall();
        }

        //checks if a robot is on the flag
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).playerRobot.posY == flag.posY && playerList.get(i).playerRobot.posX == flag.posX){
                System.out.println("Player " +String.valueOf(i+1) + " WINS!");
            }
        }



        //returns the cards from the player to the deck
        for(Player p : playerList){
            p.LockCards();

            for(Card c: p.hand){
                discard.add(c);
            }
            p.cardInputs.inputs.clear();
            p.hand.clear();
        }
        //hands new cards to the players
        for(Player p : playerList){
            for(int i = 0; i < 10-p.damage; i++){
                p.hand.add(DealCard());
            }
        }
    }

    public void HandleProgram(int _phase){
        List<Player> actionOrder = playerList;
        Collections.sort(actionOrder, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.hand.get(o1.cardInputs.inputs.get(_phase)).priority-o2.hand.get(o2.cardInputs.inputs.get(_phase)).priority;
            }
        });

        for(int i = 0; i < actionOrder.size(); i++){
            actionOrder.get(i).getCard(_phase).DoAction(actionOrder.get(i).playerRobot, board);
        }
    }

    public Card DealCard(){
        Card out;
        if(cards.size() == 1){
            out = cards.get(0);
            for(Card c: discard){
                System.out.print("Shuffle...");
                cards.add(c);
            }
            discard.clear();
        }else{
            int i = rand.nextInt(cards.size()-1);
            out = cards.get(i);
            cards.remove(i);
        }
        return out;
    }



    // overrides from application listener
    @Override
    public void create() {
        //debug/test kode
        Scanner scanner = new Scanner(System.in);
        System.out.println("select mode, 1 for singel player, 2 for multiplayer");
        if(scanner.nextInt() == 2){
            isOnline = true;
        }
        long seed = System.currentTimeMillis();
        if(isOnline){
            System.out.println("press 1 for host, 2 for client");
            if(scanner.nextInt() == 1){
                System.out.println("enter Port");
                scanner.nextLine();
                int port = scanner.nextInt();
                networkComponent = new Host(port, seed);
            }
            else {
                System.out.println("enter host IP");
                scanner.nextLine();
                String ip = scanner.nextLine();
                System.out.println("enter host Port");
                int port = scanner.nextInt();
                networkComponent = new Client(ip,port);
                Client c = (Client)networkComponent;
                currentUser = c.getPlayerNr();
                seed = c.getSeed();
            }
        }
        System.out.println(seed);
        rand.setSeed(seed);

        batch = new SpriteBatch();
        InputReader inputReader = new InputReader();
        gameBoard = new TileMap();
        flag = new Flag(5,5);
        flag.texture =  new Texture(Gdx.files.internal("src/assets/FlagTiltSolid_0.png"));
        playerList.add(new Player(new Robot(0,0)));
        playerList.add(new Player(new Robot(11,11)));

        CreateCardLibrary();
        cards = cardLibrary;

        // add dummy cards to players hand
        for(Player p : playerList){
            board.robots.add(p.playerRobot); //Adds the robots to the board for collision tracking.
            for(int i = 0; i < 10; i++){
                p.hand.add(DealCard());
            }
        }

        Gdx.input.setInputProcessor(inputReader);

    }

    private void CreateCardLibrary(){
        for(int i = 490; i <= 650; i += 10){
            cardLibrary.add(new Move1Card(i));
        }
        for(int i = 660; i <= 780; i += 10){
            cardLibrary.add(new Move2Card(i));
        }
        for(int i = 790; i <= 840; i += 10){
            cardLibrary.add(new Move3Card(i));
        }
        for(int i = 430; i <= 480; i += 10){
            cardLibrary.add(new MoveBackCard(i));
        }
        for(int i = 70; i <= 410; i += 20){
            cardLibrary.add(new TurnLeftCard(i));
        }
        for(int i = 80; i <= 420; i += 20){
            cardLibrary.add(new TurnRightCard(i));
        }
        for(int i = 10; i <= 60; i += 10){
            cardLibrary.add(new UTurnCard(i));
        }
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
