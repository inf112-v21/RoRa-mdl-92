package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Robot {
    public int Health = 10;
    public boolean initialDeathStatus=false;
    public Sprite sprite;
    public int initialCheckpoint=0;

    public int posX=0;
    public int posY=0;
    public int respawnPosX=0;
    public int respawnPosY=0;
    public int originalPosX=0;
    public int originalPosY=0;


    public boolean isPowered = true;
    public boolean timeOut = false;

    public Direction d = Direction.LEFT;

    public Robot(int x, int y){
        posX = x;
        posY = y;
        respawnPosX = x;
        respawnPosY = y;
        originalPosX=x;
        originalPosY=y;
        sprite = new Sprite(new Texture("src/assets/robot1.png"));
    }

    public void draw(SpriteBatch s){
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
    }

    public void takeDamage(int damage){
        Health -= damage;
        if(Health <= 0){
            posX = -1000;
            posY = -1000;
            timeOut = true;
        }
    }

    public void respawn(/*ArrayList<Player> players*/){
        boolean overlap = false;
//        for(Player player : players){
//            if(player.playerRobot.posX == respawnPosX && player.playerRobot.posY == respawnPosY){
//                overlap = true;
//                break;
//            }
//        }
        if(overlap){
            posX = originalPosX;
            posY = originalPosY;
        }else {
            posX = respawnPosX;
            posY = respawnPosY;
        }
        Health = 10;
    }

    public void moveForward(){
        switch (d){
            case UP: posY++; break;
            case DOWN: posY--; break;
            case RIGHT: posX++; break;
            case LEFT: posX--; break;
            default: break;
        }
    }

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

    public void moveBackwards(){
        if(d == Direction.UP){
            posY--;
        }
        else if(d == Direction.LEFT){
            posX++;
        }
        else if(d == Direction.RIGHT){
            posX--;
        }
        else if(d == Direction.DOWN){
            posY++;
        }
    }
}