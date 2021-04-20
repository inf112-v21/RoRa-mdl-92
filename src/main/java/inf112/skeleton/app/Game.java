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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sun.source.tree.BinaryTree;


public class Game implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
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
    public Board board;

    float turnTime = 0;
    boolean turnOngoing = false;

    public Game(boolean isOnline_, int nrOfPlayers_, int Port, String IP){

        isOnline = isOnline_;
        long seed = System.currentTimeMillis();
        int nrOfPlayers = 1;
        if(isOnline){
            if(IP == null){
                int port = Port;
                nrOfPlayers = nrOfPlayers_;
                if(nrOfPlayers > 3){
                    nrOfPlayers = 3;
                }
                networkComponent = new Host(port, seed, nrOfPlayers);
                nrOfPlayers++;
            }
            else {
                String ip = IP;
                int port = Port;
                networkComponent = new Client(ip,port);
                Client c = (Client)networkComponent;
                currentUser = c.getPlayerNr();
                seed = c.getSeed();
                nrOfPlayers = c.getNrOfPlayers() +1;
            }
        }
        System.out.println(seed);
        rand.setSeed(seed);

        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        InputReader inputReader = new InputReader();
        gameBoard = new TileMap();
        board = new Board(gameBoard);
        flag = new Flag(5,5);
        flag.texture =  new Texture(Gdx.files.internal("src/assets/FlagTiltSolid_0.png"));
        for(int i = 0;i < board.spawns.size(); i++){
            if(i <= nrOfPlayers){
                playerList.add(new Player(new Robot(board.spawns.get(i).x,board.spawns.get(i).y, new Sprite(new Texture("src/assets/robot1.png")))));
            }
        }
        CreateCardLibrary();
        cards = cardLibrary;

        // add dummy cards to players hand
        for(Player p : playerList){
            board.robots.add(p.playerRobot); //Adds the robots to the board for collision tracking.
            for(int i = 0; i < 9; i++){
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

    public void DoTurn() {
        turnTime = 0;
        turnOngoing = true;
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

        for(int i = 0; i < 5; i++){
            HandleProgram(i);
            turnTime += .05f;
            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    board.ExpressBeltMove();
                }
            },turnTime);
            turnTime += .05f;
            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    board.BeltMove();
                }
            }, turnTime);
            turnTime += .05f;
            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    board.PusherMove();
                }
            }, turnTime);
            turnTime += .05f;
            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    board.WheelRotate();
                }
            }, turnTime);
            turnTime += .05f;
            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    board.HoleFall();
                }
            }, turnTime);
            turnTime += .05f;
            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    board.FireLasers();
                }
            }, turnTime);
            turnTime += .05f;
        }

        //checks if a robot is on the flag
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).playerRobot.posY == flag.posY && playerList.get(i).playerRobot.posX == flag.posX){
                System.out.println("Player " +String.valueOf(i+1) + " WINS!");
            }
        }


        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                //returns the cards from the player to the deck
                for(Player p : playerList){
                    if(p.playerRobot.timeOut){
                        discard.addAll(p.lockedCards);
                        p.lockedCards.clear();
                        p.playerRobot.respawn(playerList);
                    }
                    p.LockCards();

                    discard.addAll(p.hand);
                    p.cardInputs.inputs.clear();
                    p.hand.clear();
                }
                //hands new cards to the players

                for(Player p : playerList){
                    if(p.cardInputs.shutDown){
                        p.playerRobot.Health = 10;
                    }
                    for(int i = 1; i < p.playerRobot.Health; i++){
                        p.hand.add(DealCard());
                    }
                }
                turnOngoing = false;
            }
        }, turnTime);
    }

    public void HandleProgram(int _phase){
        List<Player> actionOrder = new ArrayList<Player>();
        actionOrder.addAll(playerList); // adds the players to the list
        Collections.sort(actionOrder, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                Card c1 = o1.getCard(_phase, rand);
                Card c2 = o2.getCard(_phase, rand);
                if(c1 == null || c2 == null){return 0; }
                return c1.priority-c2.priority;
            }
        });

        for(int i = 0; i < actionOrder.size(); i++){
            int temp = i;
            turnTime += .25f;
            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    Card c = actionOrder.get(temp).getCard(_phase, rand);
                    if(c != null){
                        c.DoAction(actionOrder.get(temp).playerRobot, board);
                    }

                }
            }, turnTime);
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
        playerList.get(currentUser).drawHand(batch,font, shapeRenderer);
        for(int i = 0;i < playerList.size();i++) {
            playerList.get(i).playerRobot.draw(batch, font, i + 1);
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
            if(turnOngoing){
                return false;
            }
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
            if(turnOngoing){
                return false;
            }
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
