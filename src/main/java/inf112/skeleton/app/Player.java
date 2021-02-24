package inf112.skeleton.app;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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