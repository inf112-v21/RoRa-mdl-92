package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collection;

public class Board {
    Collection<Robot> robots;
    Collection<Wall> walls;
    Collection<ConveyorBelt> belts;
    Collection<ConveyorBelt> express;
    Collection<Pusher> pushers;
    Collection<CogWheel> cogWheels;
    Collection<Hole> hole;

    public Board(){
        robots = new ArrayList<Robot>();
        walls = new ArrayList<Wall>();
        belts = new ArrayList<ConveyorBelt>();
        express = new ArrayList<ConveyorBelt>();
        pushers = new ArrayList<Pusher>();
        cogWheels = new ArrayList<CogWheel>();
        hole = new ArrayList<Hole>();

        //Hardcoded default map
        walls.add(new Wall(0, 2,false, false, false, true));
        walls.add(new Wall(0, 4,false, false, false, true));
        walls.add(new Wall(0, 7,false, false, false, true));
        walls.add(new Wall(0, 9,false, false, false, true));
        walls.add(new Wall(1, 1,false, true, false, true));
        walls.add(new Wall(2, 0,false, false, true, false));
        walls.add(new Wall(2, 3,false, true, false, false));
        walls.add(new Wall(2, 4,false, false, true, false));
        walls.add(new Wall(2, 9,true, false, false, false));
        walls.add(new Wall(2, 10,false, true, false, false));
        walls.add(new Wall(2, 11,true, false, false, false));
        walls.add(new Wall(4, 0,false, false, true, false));
        walls.add(new Wall(4, 11,true, false, false, false));
        walls.add(new Wall(5, 8,false, false, true, true));
        walls.add(new Wall(5, 10,false, true, false, false));
        walls.add(new Wall(6, 3,false, true, false, false));
        walls.add(new Wall(6, 6,false, false, false, true));
        walls.add(new Wall(7, 0,false, false, true, false));
        walls.add(new Wall(7, 1,false, false, false, true));
        walls.add(new Wall(7, 11,true, false, false, false));
        walls.add(new Wall(8, 5,false, false, true, false));
        walls.add(new Wall(8, 7,true, false, false, false));
        walls.add(new Wall(9, 0,false, false, true, false));
        walls.add(new Wall(9, 11,true, false, false, false));
        walls.add(new Wall(10, 1,false, false, false, true));
        walls.add(new Wall(10, 5,false, false, true, false));
        walls.add(new Wall(11, 2,false, true, false, false));
        walls.add(new Wall(11, 4,false, true, false, false));
        walls.add(new Wall(11, 7,false, true, false, false));
        walls.add(new Wall(11, 9,false, true, false, false));

        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 11)));
        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 10)));
        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 9)));
        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 8)));
        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 7)));
        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 6)));
        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 5)));
        express.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 4)));
        express.add(new ConveyorBelt(Direction.LEFT, new Coordinate(4, 9)));
        express.add(new ConveyorBelt(Direction.LEFT, new Coordinate(5, 9)));
        express.add(new ConveyorBelt(Direction.DOWN, new Coordinate(5, 10)));
        express.add(new ConveyorBelt(Direction.DOWN, new Coordinate(5, 11)));

        belts.addAll(express);
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(1, 0)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(1, 2)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(1, 3)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(1, 4)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(1, 5)));
        belts.add(new ConveyorBelt(Direction.RIGHT, new Coordinate(0, 5)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(0, 6)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(1, 6)));
        belts.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 0)));
        belts.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 1)));
        belts.add(new ConveyorBelt(Direction.UP, new Coordinate(3, 2)));
        belts.add(new ConveyorBelt(Direction.RIGHT, new Coordinate(5, 7)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(6, 4)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(6, 5)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(6, 6)));
        belts.add(new ConveyorBelt(Direction.UP, new Coordinate(6, 8)));
        belts.add(new ConveyorBelt(Direction.UP, new Coordinate(6, 9)));
        belts.add(new ConveyorBelt(Direction.UP, new Coordinate(6, 10)));
        belts.add(new ConveyorBelt(Direction.UP, new Coordinate(6, 11)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(7, 6)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(9, 6)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(10, 6)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(11, 6)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(10, 10)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(11, 10)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(9, 10)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(9, 9)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(8, 9)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(8, 10)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(8, 11)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(8, 0)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(8, 1)));
        belts.add(new ConveyorBelt(Direction.DOWN, new Coordinate(8, 2)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(9, 3)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(10, 3)));
        belts.add(new ConveyorBelt(Direction.LEFT, new Coordinate(11, 3)));

        cogWheels.add(new CogWheelLeft(2,5));
        cogWheels.add(new CogWheelLeft(5,3));
        cogWheels.add(new CogWheelLeft(6,7));
        cogWheels.add(new CogWheelLeft(8,3));

        cogWheels.add(new CogWheelRight(2,6));
        cogWheels.add(new CogWheelRight(4,3));
        cogWheels.add(new CogWheelRight(8,6));

        hole.add(new Hole (5,2));
        hole.add(new Hole (9,2));
        hole.add(new Hole (9,8));
        hole.add(new Hole (7,5));
        hole.add(new Hole (1,10));
    }

    //A function to check is movement is valid in a direction, will also handle shoving.
    public boolean CanGo(Direction dir, Coordinate pos){
        //Checks if the current space has a blocking wall.
        for(Wall w: walls){
            if(w.pos.equals(pos)){
                System.out.println("There's a Wall Here");
                if(w.CollideExit(dir)){
                    System.out.println("That's a Wall.");
                    return false;
                }
            }
        }

        //generate the destination tile coordinate
        Coordinate next = pos.Step(dir);

        //Checks if the target space has a blocking wall.
        for(Wall w: walls){
            if(w.pos.equals(next)){
                System.out.println("There's a Wall Here");
                if(w.CollideEnter(dir)){
                    System.out.println("That's a Wall.");
                    return false;
                }
            }
        }

        //Checks if the target space has a robot.
        for(Robot r: robots){
            if((r.posX == next.x)&&(r.posY == next.y)){
                if (CanGo(dir, next)){
                    r.posY = next.Step(dir).y;
                    r.posX = next.Step(dir).x;
                }else{
                    return false;
                }
            }
        }

        return true;
    }

    //Does a regular Conveyor Belt movement for all robots
    public void BeltMove(){
        for(Robot r: robots){
            ConveyorBelt here = null;
            for (ConveyorBelt b: belts){
                if((b.pos.x == r.posX)&&(b.pos.y == r.posY)){
                    here = b;
                }
            }
            if (here == null){
                return;
            }
            if (CanGo(here.dir, here.pos)){
                Coordinate next = here.pos.Step(here.dir);
                r.posX = next.x;
                r.posY = next.y;
                for(ConveyorBelt b: belts){
                    if(b.pos.equals(next)){
                        switch (CompareDir(here.dir, b.dir)){
                            case LEFT: r.turnLeft();
                            case RIGHT: r.turnRight();
                            default: break;
                        }
                    }
                }
            }
        }
    }

    public void WheelRotate(){
        for(Robot r: robots ){
            for (CogWheel b: cogWheels){
                if((b.X == r.posX)&&(b.Y == r.posY)){
                    b.DoAction(r);
                }
            }
        }
    }

    //makes robots fall and die (takes 10 damage and respawns) when walking on a hole
    public void HoleFall(){
        for(Robot r: robots){
            for (Hole b: hole){
                if ((b.X==r.posX)&&(b.Y == r.posY)){
                    b.fallInHole(r);
                }
            }
        }
    }

    //Does the express Conveyor Belt Movement for all robots
    public void ExpressBeltMove(){
        for(Robot r: robots){
            ConveyorBelt here = null;
            for (ConveyorBelt b: express){
                if((b.pos.x == r.posX)&&(b.pos.y == r.posY)){
                    here = b;
                }
            }
            if (here == null){
                return;
            }
            if (CanGo(here.dir, here.pos)){
                Coordinate next = here.pos.Step(here.dir);
                r.posX = next.x;
                r.posY = next.y;
                for(ConveyorBelt b: belts){
                    if(b.pos.equals(next)){
                        switch (CompareDir(here.dir, b.dir)){
                            case LEFT: r.turnLeft();
                            case RIGHT: r.turnRight();
                            default: break;
                        }
                    }
                }
            }
        }
    }

    //Does the Pusher movement for all robots.
    public void PusherMove(){
        Coordinate _robotPos;
        for (Robot r: robots) {
            _robotPos = new Coordinate(r.posX, r.posY);
            Pusher here = null;
            for(Pusher p: pushers){
                if (p.target.equals(_robotPos)){
                    here = p;
                }
            }
            if (here == null){
                return;
            }
            if (CanGo(here.dir, here.target)) {
                Coordinate next = here.target.Step(here.dir);
                r.posX = next.x;
                r.posY = next.y;
            }
        }
    }

    public Direction CompareDir(Direction in, Direction out){
        switch (in){
            case LEFT:
                switch (out){
                    case LEFT: return Direction.UP;
                    case DOWN: return Direction.LEFT;
                    case RIGHT: return Direction.DOWN;
                    case UP: return Direction.RIGHT;
                }
                break;
            case RIGHT:
                switch (out){
                    case LEFT: return Direction.DOWN;
                    case DOWN: return Direction.RIGHT;
                    case RIGHT: return Direction.UP;
                    case UP: return Direction.LEFT;
                }
                break;
            case UP:
                return out;
            case DOWN:
                switch (out){
                    case LEFT: return Direction.RIGHT;
                    case DOWN: return Direction.UP;
                    case RIGHT: return Direction.LEFT;
                    case UP: return Direction.DOWN;
                }
                break;

        }
        return Direction.UP;
    }

}

class Coordinate{
    public int x;
    public int y;

    public Coordinate(int _x, int _y){
        x = _x;
        y = _y;
    }

    public boolean equals(Coordinate o) {
        if ((o.x == x)&&(o.y == y)){
            return true;
        }
        return false;
    }

    public Coordinate Step(Direction dir){
        switch (dir){
            case UP: return new Coordinate(x, y+1);
            case DOWN: return new Coordinate(x, y-1);
            case LEFT: return new Coordinate(x-1, y);
            case RIGHT: return new Coordinate(x+1, y);
            default: return this;
        }
    }
}

class Wall{
    Coordinate pos;

    boolean faceUp;
    boolean faceDown;
    boolean faceLeft;
    boolean faceRight;

    public Wall(int x, int y, boolean up, boolean right, boolean down, boolean left){
        pos = new Coordinate(x, y);
        faceUp = up;
        faceRight = right;
        faceDown = down;
        faceLeft = left;
    }

    public boolean CollideExit(Direction exit) {
        switch (exit) {
            case UP:
                if (faceUp) {
                    return true;
                }
                break;
            case DOWN:
                if (faceDown) {
                    return true;
                }
                break;
            case LEFT:
                if (faceLeft) {
                    return true;
                }
                break;
            case RIGHT:
                if (faceRight) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
    public boolean CollideEnter(Direction enter){
        switch (enter){
            case DOWN: if(faceUp){return true;} break;
            case UP: if(faceDown){return true;} break;
            case RIGHT: if(faceLeft){return true;} break;
            case LEFT: if(faceRight){return true;} break;
            default: break;
        }
        return false;
    }
}

class ConveyorBelt {
    Direction dir;
    Coordinate pos;

    public ConveyorBelt(Direction _dir, Coordinate _pos){
        dir = _dir;
        pos = _pos;
    }
}

class Pusher {
    Direction dir;
    Coordinate pos;
    Coordinate target;

    public Pusher(Direction _dir, Coordinate _pos){
        dir = _dir;
        pos = _pos;
        target = pos.Step(dir);
    }
}