package inf112.skeleton.app;

public class Robot {
    public int initialHealth=100;
    public boolean initialDeathStatus=false;

    public int initialCheckpoint=0;

    public int posX=0;
    public int posY=0;

    public Direction d = Direction.LEFT;

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
