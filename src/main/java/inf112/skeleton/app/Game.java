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


public class Game implements ApplicationListener {

    public int turn=0;
    public List<Player> playerList = new ArrayList<>();
    Cards cards = new Cards();
    Map<Player, List<Integer>> registers = new HashMap<>();
    public Board gameBoard;



    public void DoTurn() {
        turn += 1;
        if (turn == 1) {

        }
        for (Player p : playerList) {
            cards.DealCards(p);
            int index = 0;
            registers.put(p, p.ProgramRegisters());
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
        for (int i=0; i<5; i++) {
            int randCard = ThreadLocalRandom.current().nextInt(0, 9);
            registers.add(cardsList.get(randCard));
            cardsList.remove(randCard);
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
                playerRobot.posX++;
                break;
            case 21:
                playerRobot.posX--;
                break;
            case 19:
                playerRobot.posY++;
                break;
            case 20:
                playerRobot.posY--;
                break;
        }

        //checking if robot is on flag
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
