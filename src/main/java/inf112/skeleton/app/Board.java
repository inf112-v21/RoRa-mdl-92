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
    Collection<LaserShooter> lasers;
    public ArrayList<Coordinate> spawns;
    int maxX = 15;
    int maxY = 11;
    int minX = 0;
    int minY = 0;

    public Board(TileMap tileMap){
        robots = new ArrayList<Robot>();
        walls = new ArrayList<Wall>();
        belts = new ArrayList<ConveyorBelt>();
        express = new ArrayList<ConveyorBelt>();
        pushers = new ArrayList<Pusher>();
        cogWheels = new ArrayList<CogWheel>();
        hole = new ArrayList<Hole>();
        lasers = new ArrayList<>();
        spawns = new ArrayList<Coordinate>();


        for(Coordinate c : tileMap.getMapObjectLocations("WallRight")){
            walls.add(new Wall(c.x, c.y,false, true, false, false));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("WallLeft")){
            walls.add(new Wall(c.x, c.y,false, false, false, true));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("WallUp")){
            walls.add(new Wall(c.x, c.y,true, false, false, false));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("WallDown")){
            walls.add(new Wall(c.x, c.y,false, false, true, false));
        }



        for(Coordinate c : tileMap.getMapObjectLocations("ExpressConveyorBeltUp")){
            express.add(new ConveyorBelt(Direction.UP, c));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("ExpressConveyorBeltDown")){
            express.add(new ConveyorBelt(Direction.DOWN, c));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("ExpressConveyorBeltLeft")){
            express.add(new ConveyorBelt(Direction.LEFT, c));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("ExpressConveyorBeltRight")){
            express.add(new ConveyorBelt(Direction.RIGHT, c));
        }

        belts.addAll(express);

        for(Coordinate c : tileMap.getMapObjectLocations("ConveyorBeltUp")){
            belts.add(new ConveyorBelt(Direction.UP, c));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("ConveyorBeltDown")){
            belts.add(new ConveyorBelt(Direction.DOWN, c));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("ConveyorBeltLeft")){
            belts.add(new ConveyorBelt(Direction.LEFT, c));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("ConveyorBeltRight")){
            belts.add(new ConveyorBelt(Direction.RIGHT, c));
        }

        for(Coordinate c : tileMap.getMapObjectLocations("LeftCogs")){
            cogWheels.add(new CogWheelLeft(c.x,c.y));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("RightCogs")){
            cogWheels.add(new CogWheelRight(c.x,c.y));
        }

        for(Coordinate c : tileMap.getMapObjectLocations("Holes")){
            hole.add(new Hole(c.x,c.y));
        }

        for(Coordinate c : tileMap.getMapObjectLocations("Laser1")){
            lasers.add(new LaserShooter(Direction.LEFT, c, 1));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("Laser2")){
            lasers.add(new LaserShooter(Direction.LEFT, c, 2));
        }
        for(Coordinate c : tileMap.getMapObjectLocations("Laser3")){
            lasers.add(new LaserShooter(Direction.LEFT, c, 3));
        }

        for(Coordinate c : tileMap.getMapObjectLocations("Spawn")){
            spawns.add(c);
        }
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

    //A function to check if a laser collides with anything.
    public boolean LaserCheck(Direction dir, Coordinate pos, int str){
        if(pos.x < minX || pos.y < minY || pos.x > maxX || pos.y > maxY){
            return false;
        }
        //Checks if the current space has a blocking wall.
        for(Wall w: walls){
            if(w.pos.equals(pos)){
                if(w.CollideExit(dir)){
                    return false;
                }
            }
        }

        //generate the destination tile coordinate
        Coordinate next = pos.Step(dir);

        //Checks if the target space has a blocking wall.
        for(Wall w: walls){
            if(w.pos.equals(next)){
                if(w.CollideEnter(dir)){
                    return false;
                }
            }
        }

        //Checks if the target space has a robot.
        for(Robot r: robots){
            if((r.posX == next.x)&&(r.posY == next.y)){
                r.takeDamage(str);
                return false;
            }
        }

        return LaserCheck(dir, pos.Step(dir), str);
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
                continue;
            }
            if (CanGo(here.dir, here.pos)){
                Coordinate next = here.pos.Step(here.dir);
                r.posX = next.x;
                r.posY = next.y;
                for(ConveyorBelt b: belts){
                    if(b.pos.equals(next)){
                        if(CompareDir(here.dir, b.dir) == Direction.LEFT){ r.turnLeft();}
                        if(CompareDir(here.dir, b.dir) == Direction.RIGHT){ r.turnRight();}
                    }
                }
            }
        }
    }

    //foreløpig usikkert om denne skjønner hvilken type hjul som skal snurre
    //makes robots rotate 90 degrees left/right (depending on cogwheel type)
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
            if(r.posX < minX || r.posX > maxX || r.posY < minY || r.posY > maxY){ // robot falls in hole if they exit map
                Hole h = new Hole(-1,-1);
                h.fallInHole(r);
            }
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
                continue;
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
                continue;
            }
            if (CanGo(here.dir, here.target)) {
                Coordinate next = here.target.Step(here.dir);
                r.posX = next.x;
                r.posY = next.y;
            }
        }
    }

    /*public void FireLasers(){
        for(Robot r: robots){
            LaserCheck(r.d, new Coordinate(r.posX, r.posY), 1);
        }
        for(LaserShooter l: lasers){
            Robot target = null;
            for(Robot r: robots){
                if (r.posX == l.pos.x && r.posY == l.pos.y){
                    target = r;
                }
            }
            if(target != null){
                target.takeDamage(l.str);
            }else{
                LaserCheck(l.dir, l.pos, l.str);
            }
        }
    }*/

    public void FireLasers(){
        for(Robot r: robots){
            for(LaserShooter l: lasers){
                if(l.pos.x == r.posX && l.pos.y == r.posY){
                    r.takeDamage(l.str);
                }
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

class LaserShooter{
    Coordinate pos;
    Direction dir;
    int str;

    public LaserShooter(Direction _dir, Coordinate _pos, int _str){
        dir = _dir;
        pos = _pos;
        str = _str;
    }
}