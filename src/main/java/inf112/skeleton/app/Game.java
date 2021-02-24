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
    Cards cards = new Cards();
    Map<Player, List<Integer>> playerRegisters = new HashMap<>();
    public Board gameBoard;
    public Map<Integer, Map<Player, Integer>> registerHistory = new HashMap<>();


    public void DoTurn() {
        turn += 1;

        //Sassan's non-comment code
        for (Player p : playerList) {
            cards.DealCards(p);
            int index = 0;
            playerRegisters.put(p, p.ProgramRegisters());
        }
        CompleteRegisters();
        for(int phase = 0; phase < 5; phase++) {

            //HandleProgram(Board.getRobots(), phase);    //dependant on getting robots from Board.
            HandleProgram(new ArrayList<Robot>(), phase); //Substitute for lone above.

        }
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

//temp class Player
class Player implements InputProcessor {

    public List<Integer> cardsList = new ArrayList<>();
    public int damage=0;
    public Robot playerRobot;

    public List<Integer> ProgramRegisters() {
        List<Integer> registers = new ArrayList<>();
        int bound = 8;
        for (int i=0; i<5; i++) {
            int randCard = ThreadLocalRandom.current().nextInt(0, bound);
            registers.add(cardsList.get(randCard));
            cardsList.remove(randCard);
            bound--;
        }
        return registers;
    }

    public Player(Robot robot){
        playerRobot = robot;
    }

    @Override
    public boolean keyDown(int i) {
        switch (i){
            case 22:
                playerRobot.turnRight();
                break;
            case 21:
                playerRobot.turnLeft();
                break;
            case 19:
                playerRobot.moveForward();
                break;
            case 20:
                playerRobot.moveBackwards();
                break;
        }

        //checking if robot is on flag, this shouldn't be here
        for(int z = 0; z < Board.allFlags.length; z++){
            if ((playerRobot.posX == Board.allFlags[z].posX) && (playerRobot.posY == Board.allFlags[z].posY)){
                Board.allFlags[z].visitedP1 = true;
            }
        }
        if (Board.allFlags[0].visitedP1 && Board.allFlags[1].visitedP1 && Board.allFlags[2].visitedP1){
            System.out.println("Yay, You Won");
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
        return false;
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

//temp class Cards
class Cards {

    public void DealCards(Player player) {
        for (int i = 0; i < 9- player.damage; i++) {
            int randCard = ThreadLocalRandom.current().nextInt(1, 6);
            player.cardsList.add(randCard);
        }
    }

}
