package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;

import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


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
    public Board board;
    public boolean gameOver = false;
    public boolean waitingForPlayers = false;

    float turnTime = 0;
    boolean turnOngoing = false;

    public Game(boolean isOnline_, int nrOfPlayers_, int Port, String IP){

        isOnline = isOnline_;
        long seed = System.currentTimeMillis();
        int nrOfPlayers = 1;
        if(isOnline){
            if(IP == null){ // if you are a host
                int port = Port;
                nrOfPlayers = nrOfPlayers_;
                if(nrOfPlayers > 3){
                    nrOfPlayers = 3;
                }
                networkComponent = new Host(port, seed, nrOfPlayers);
            }
            else {
                String ip = IP;
                int port = Port;
                networkComponent = new Client(ip,port);
                Client c = (Client)networkComponent;
                currentUser = c.getPlayerNr();
                seed = c.getSeed(); // gets the seed that will be used for this game
                nrOfPlayers = c.getNrOfPlayers();
            }
        }
        rand.setSeed(seed);

        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        InputReader inputReader = new InputReader();
        gameBoard = new TileMap();
        board = new Board(gameBoard);

        // Random Flag Placement
        for (int i = 0; i < 5; i++){
            int curX = rand.nextInt(board.maxX-board.minX)+board.minX;
            int curY = rand.nextInt(board.maxY-board.minY)+board.minY;
            boolean validFlag = true;

            for(Hole h: board.hole){
                if(h.X == curX && h.Y==curY){
                    validFlag = false;
                }
            }

            Coordinate curXY = new Coordinate(curX, curY);
            for(ConveyorBelt belt: board.belts){
                if(belt.pos.equals(curXY)){
                    validFlag = false;
                    for(ConveyorBelt beltFrom: board.belts){
                        if(beltFrom.pos.Step(beltFrom.dir).equals(curXY)){
                            validFlag = true;
                        }
                    }
                }
            }
            for(ConveyorBelt belt: board.express){
                if(belt.pos.equals(curXY)){
                    validFlag = false;
                    for(ConveyorBelt beltFrom1: board.express){
                        if(beltFrom1.pos.Step(beltFrom1.dir).equals(curXY)){
                            for(ConveyorBelt beltFrom2: board.express){
                                if(beltFrom2.pos.Step(beltFrom2.dir).equals(beltFrom1.pos)){
                                validFlag = true;
                                }
                            }
                        }
                    }
                }
            }

            if(validFlag) {
                board.flags.add(new Flag(curX, curY));
            }else{
                i--;
            }
        }
        board.flags.get(0).texture = Flag.texNext;


        for(int i = 0;i < board.spawns.size(); i++){
            if(i <= nrOfPlayers){
                playerList.add(new Player(new Robot(board.spawns.get(i).x,board.spawns.get(i).y, new Sprite(new Texture("src/assets/robot1.png")))));
            }
        }

        CreateCardLibrary();
        cards = cardLibrary;


        for(Player p : playerList){
            board.robots.add(p.playerRobot); //Adds the robots to the board for collision tracking.
            for(int i = 0; i < 9; i++){
                p.hand.add(DealCard());
            }
        }
        CheckFlags();

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

    /*  This function handles the processing of a turn.
        Primarily adds functions to a queue using com.badlogic.gdx.utils.Timer, which are completed later.
        When initiated turnOngoing is set to true and turn increases by 1.
        When the last task in the queue is done, turnOngoing is set to false.
     */
    public void DoTurn() {
        turnTime = 0;
        turnOngoing = true;
        turn += 1;
        if(isOnline){
            //get inputs from other players
            waitingForPlayers = true;
            ArrayList<playerInputs> otherPlayerInputs = networkComponent.communicateToPlayers(playerList.get(currentUser).cardInputs);
            waitingForPlayers = false;
            for(int i = 0; i < playerList.size(); i++){ // set inputs to respective players
                if(i != currentUser){
                    playerList.get(i).cardInputs = otherPlayerInputs.get(0);
                    otherPlayerInputs.remove(0);
                }
            }
        }

        // The 5 rounds of turns, first inputs, then board elements, then checking for victory
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


            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    if(CheckFlags() != null){
                        gameOver = true;
                        com.badlogic.gdx.utils.Timer.instance().clear();
                    }
                }
            }, turnTime);
            turnTime += .01f;
        }

        // Turn cleanup: card locking and respawns, clearing hand, resolving shutdowns, and then dealing new cards.
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
                        discard.addAll(p.lockedCards);
                        p.lockedCards.clear();
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

    // Compares each players input for a given round, and schedules them on the timer in decreasing order of priority.
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

    // Pop a random card from the  main deck, if the deck is empty, the discard deck is shuffled into the main deck.
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

    // Checks if any robots are on a flag, if a player has won, will return player. Else returns null.
    public Player CheckFlags(){
        for (Player p: playerList){
            if(board.flags.get(p.currentFlag).IsAt(new Coordinate(p.playerRobot.posX, p.playerRobot.posY))){
                if(p == playerList.get(currentUser)){
                    board.flags.get(p.currentFlag).texture = Flag.texReached;
                }

                p.playerRobot.respawnPosX = board.flags.get(p.currentFlag).posX;
                p.playerRobot.respawnPosY = board.flags.get(p.currentFlag).posY;

                p.currentFlag++;
                if (p.currentFlag == board.flags.size()) {
                    return p;
                }
                if(p == playerList.get(currentUser)){
                    board.flags.get(p.currentFlag).texture = Flag.texNext;
                }
            }

        }
        return null;
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
            font.setColor(Color.GOLDENROD);
            if(i == currentUser){
                font.setColor(Color.GREEN);
            }
            playerList.get(i).playerRobot.draw(batch, font, i + 1);
        }
        for (int i = 0; i < board.flags.size(); i++) {
            Flag f = board.flags.get(i);
            batch.draw(f.texture,f.posX*83,f.posY*83);
            font.draw(batch, Integer.toString(i+1), f.posX*83+41, f.posY*83+59);
        }
        font.setColor(Color.GREEN);
        font.getData().setScale(3);
        if(waitingForPlayers){
            font.draw(batch, "Waiting For Other Players", 400, 400);
        }
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
        //a thread that runs the turn so that the application doesn't stop responding when waiting for the other players
        class DoTurnThread extends Thread{
            @Override
            public void run(){
                DoTurn();
            }
        }

        @Override
        public boolean keyDown(int i) {
            if(turnOngoing){
                return false;
            }
            if(i == 44){
                DoTurnThread t = new DoTurnThread();
                t.start();
            }
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
            if(playerList.get(currentUser).touchUp()){

                DoTurnThread t = new DoTurnThread();
                t.start();
            }
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
