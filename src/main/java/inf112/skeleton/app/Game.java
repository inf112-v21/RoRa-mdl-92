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

    public int turn=0;
    public List<Player> playerList = new ArrayList<>();
    public List<Card> cards = new ArrayList<Card>();
    public List <Card> discard = new ArrayList<Card>();
    Map<Player, List<Integer>> playerRegisters = new HashMap<>();
    public Board gameBoard;
    public Map<Integer, Map<Player, Integer>> registerHistory = new HashMap<>();
    public Random rand = new Random();


    public void DoTurn() {
        turn += 1;

        //Deal Cards to players.
        for(Player p: playerList){
            Player.takeCards(this);
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
        gameBoard = new Board();
        Player p1 = new Player(gameBoard.robot);
        Gdx.input.setInputProcessor(p1);
        //Player p2 = new Player();
        //Player p3 = new Player();
        //Player p4 = new Player();
        playerList.addAll(Arrays.asList(p1));

    }

    @Override
    public void dispose() {
        gameBoard.Dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        gameBoard.Render();
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

}
