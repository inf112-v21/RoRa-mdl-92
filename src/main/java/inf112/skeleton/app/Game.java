package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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
            Player p1 = new Player();
            Player p2 = new Player();
            Player p3 = new Player();
            Player p4 = new Player();
            playerList.addAll(Arrays.asList(p1,p2,p3,p4));
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
class Player {

    public List<Integer> cardsList = new ArrayList<>();
    public int damage=0;

    public List<Integer> ProgramRegisters() {
        List<Integer> registers = new ArrayList<>();
        for (int i=0; i<5; i++) {
            int randCard = ThreadLocalRandom.current().nextInt(0, 9);
            registers.add(cardsList.get(randCard));
            cardsList.remove(randCard);
        }
        return registers;
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
