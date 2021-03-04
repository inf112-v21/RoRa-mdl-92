package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Robot {
    public int initialHealth=100;
    public boolean initialDeathStatus=false;
    public Sprite sprite;
    public int initialCheckpoint=0;

    public int posX=0;
    public int posY=0;

    public boolean isPowered = true;

    public Direction d = Direction.LEFT;

    public Robot(int x, int y){
        posX = x;
        posY = y;
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

    public void moveForward(){
        if(d == Direction.UP){
            posY++;
        }
        else if(d == Direction.LEFT){
            posX--;
        }
        else if(d == Direction.RIGHT){
            posX++;
        }
        else if(d == Direction.DOWN){
            posY--;
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
