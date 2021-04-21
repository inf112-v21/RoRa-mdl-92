package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Robot {
    public int Health = 10;
    public Sprite sprite;

    public int posX=0;
    public int posY=0;
    public int respawnPosX=0;
    public int respawnPosY=0;
    public int originalPosX=0;
    public int originalPosY=0;


    public boolean isPowered = true;
    public boolean timeOut = false;

    public Direction d = Direction.LEFT;

    public Robot(int x, int y, Sprite s){
        posX = x;
        posY = y;
        respawnPosX = x;
        respawnPosY = y;
        originalPosX=x;
        originalPosY=y;

        sprite = s;
    }

    //draws robot on board
    public void draw(SpriteBatch s, BitmapFont font, int playerNr){
        int rotation = 0;
        if(d == Direction.RIGHT){
            rotation = -90;
        }
        else if(d == Direction.DOWN){
            rotation = 180;
        }
        else if(d == Direction.LEFT){
            rotation = 90;
        }
        s.draw(new TextureRegion(sprite.getTexture()),83*posX,83*posY,41,41,83,83,1,1,rotation);
        font.getData().setScale(1.5f);
        font.draw(s,"Player "+Integer.toString(playerNr),83*posX+3,83*posY+83);
    }

    //Deals x amount of damage and respawns robot if health is 0 or less
    public void takeDamage(int damage){
        Health -= damage;
        if(Health <= 0){
            posX = -1000;
            posY = -1000;
            timeOut = true;
        }
    }

    //robot respawns in default position with full health
    public void respawn(List<Player> players){
        boolean overlap = false;
        for(Player player : players){
            if(player.playerRobot == this){
                break;
            }
            if(player.playerRobot.posX == respawnPosX && player.playerRobot.posY == respawnPosY){
                overlap = true;
                break;
            }
        }
        System.out.print("Respawned");
        if(overlap){
            posX = originalPosX;
            posY = originalPosY;
        }else {
            posX = respawnPosX;
            posY = respawnPosY;
        }
        Health = 10;
        timeOut = false;
    }

    //robot moves forward
    public void moveForward(Board board){
        if (board.CanGo(d, new Coordinate(posX, posY))) {
            switch (d) {
                case UP:
                    posY++;
                    break;
                case DOWN:
                    posY--;
                    break;
                case RIGHT:
                    posX++;
                    break;
                case LEFT:
                    posX--;
                    break;
                default:
                    break;
            }
        }
    }

    //robot turns right
    public void turnRight(){
        if(d == Direction.UP){
            d = Direction.RIGHT;
        }
        else if(d == Direction.LEFT){
            d = Direction.UP;
        }
        else if(d == Direction.RIGHT){
            d = Direction.DOWN;
        }
        else if(d == Direction.DOWN){
            d = Direction.LEFT;
        }
    }

    //robot turns left
    public void turnLeft(){
        if(d == Direction.UP){
            d = Direction.LEFT;
        }
        else if(d == Direction.LEFT){
            d = Direction.DOWN;
        }
        else if(d == Direction.RIGHT){
            d = Direction.UP;
        }
        else if(d == Direction.DOWN){
            d = Direction.RIGHT;
        }
    }

    //robot moves backwards
    public void moveBackwards(Board board){
        Direction reverse = d;
        switch (d){
            case UP: reverse = Direction.DOWN; break;
            case DOWN: reverse = Direction.UP; break;
            case LEFT: reverse = Direction.RIGHT; break;
            case RIGHT: reverse = Direction.LEFT; break;
        }
        if (board.CanGo(reverse, new Coordinate(posX, posY))) {
            if (d == Direction.UP) {
                posY--;
            } else if (d == Direction.LEFT) {
                posX++;
            } else if (d == Direction.RIGHT) {
                posX--;
            } else if (d == Direction.DOWN) {
                posY++;
            }
        }
    }
}