package inf112.skeleton.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BoardTest {
    Board board;
    Robot testSubject;

    @Before
    public void Setup(){
        board = new Board();
        testSubject = new Robot(0,0, null);
        board.robots.add(testSubject);

        board.cogWheels.clear();
        board.walls.clear();
        board.pushers.clear();
        board.lasers.clear();
        board.belts.clear();
        board.express.clear();
        board.hole.clear();
    }

    @Test
    public void RobotMoveForward(){
        testSubject.posY = 0;
        testSubject.posX = 0;
        testSubject.d = Direction.RIGHT;

        testSubject.moveForward(board);
        assertTrue(testSubject.posX == 1 &&testSubject.posY == 0);
    }

    @Test
    public void RobotMoveBackwards(){
        testSubject.posY = 0;
        testSubject.posX = 0;
        testSubject.d = Direction.LEFT;

        testSubject.moveForward(board);
        assertTrue(testSubject.posX == -1 &&testSubject.posY == 0);
    }

    @Test
    public void WallBlocksEntryIntoTile(){
        board.walls.add(new Wall(1, 0, false, false, false, true));

        testSubject.posY = 0;
        testSubject.posX = 0;
        testSubject.d = Direction.RIGHT;
        testSubject.moveForward(board);
        assertTrue(testSubject.posX == 0 && testSubject.posY == 0);

        testSubject.posY = 0;
        testSubject.posX = 0;
        testSubject.d = Direction.LEFT;
        testSubject.moveBackwards(board);
        assertTrue(testSubject.posX == 0 && testSubject.posY == 0);
    }

    @Test
    public void WallBlocksExitOutOfTile(){
        board.walls.add(new Wall(0, 0, false, true, false, false));

        testSubject.posY = 0;
        testSubject.posX = 0;
        testSubject.d = Direction.RIGHT;
        testSubject.moveForward(board);
        assertTrue(testSubject.posX == 0 && testSubject.posY == 0);testSubject.posY = 0;

        testSubject.posX = 0;
        testSubject.d = Direction.LEFT;
        testSubject.moveBackwards(board);
        assertTrue(testSubject.posX == 0 && testSubject.posY == 0);
    }

    @Test
    public void RobotsCanTurnRight(){
        testSubject.d = Direction.UP;
        testSubject.turnRight();
        assertTrue(testSubject.d == Direction.RIGHT);

        testSubject.d = Direction.RIGHT;
        testSubject.turnRight();
        assertTrue(testSubject.d == Direction.DOWN);

        testSubject.d = Direction.DOWN;
        testSubject.turnRight();
        assertTrue(testSubject.d == Direction.LEFT);

        testSubject.d = Direction.LEFT;
        testSubject.turnRight();
        assertTrue(testSubject.d == Direction.UP);
    }

    @Test
    public void RobotsCanTurnLeft(){
        testSubject.d = Direction.UP;
        testSubject.turnLeft();
        assertTrue(testSubject.d == Direction.LEFT);

        testSubject.d = Direction.RIGHT;
        testSubject.turnLeft();
        assertTrue(testSubject.d == Direction.UP);

        testSubject.d = Direction.DOWN;
        testSubject.turnLeft();
        assertTrue(testSubject.d == Direction.RIGHT);

        testSubject.d = Direction.LEFT;
        testSubject.turnLeft();
        assertTrue(testSubject.d == Direction.DOWN);
    }

    @Test
    public void ConveyorBeltsMoveNormal(){
        board.belts.add(new ConveyorBelt(Direction.RIGHT, new Coordinate(0,0)));
        testSubject.posX = 0;
        testSubject.posY = 0;

        board.BeltMove();

        assertTrue(testSubject.posX == 1 && testSubject.posY == 0);
    }

    @Test
    public void ConveyorBeltsMoveOntoCorner(){
        board.belts.add(new ConveyorBelt(Direction.RIGHT, new Coordinate(0,0)));
        board.belts.add(new ConveyorBelt(Direction.UP, new Coordinate(1,0)));
        testSubject.posX = 0;
        testSubject.posY = 0;
        testSubject.d = Direction.RIGHT;

        board.BeltMove();

        assertTrue(testSubject.posX == 1 && testSubject.posY == 0 && testSubject.d == Direction.UP);
    }

    @Test
    public void LaserDoesDamage(){
        testSubject.posX = 0;
        testSubject.posY = 0;

        Robot otherRobot = new Robot(0,0, null);
        board.robots.add(otherRobot);

        otherRobot.posX = 1;
        otherRobot.posY = 0;

        board.lasers.add(new LaserShooter(Direction.RIGHT, new Coordinate(-1, 0), 1));

        board.FireLasers();

        assertTrue(testSubject.Health == 9);
        assertTrue(otherRobot.Health == 10);
    }

}
