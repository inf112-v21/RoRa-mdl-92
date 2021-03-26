package inf112.skeleton.app;

import junit.framework.TestCase;

import java.util.ArrayList;

public class RobotTest extends TestCase {

    public void testDraw() {

    }

    public void testTakeDamage() {
        Robot robot = new Robot(0,0, null);
        assertEquals(10, robot.Health);
        robot.takeDamage(5);
        assertEquals(5, robot.Health);

        Robot robot2 = new Robot(0,0, null);
        assertEquals(10, robot2.Health);
        robot2.takeDamage(10);
        assertTrue(robot2.posX == -1000 && robot2.posY == -1000);
    }

    public void testRespawn() {
        Robot robot = new Robot(0,0,null);
        ArrayList players = new ArrayList<Player>();

        assertTrue(robot.posX == 0 && robot.posY == 0);
        robot.posX = 5;
        robot.posY = 5;
        robot.respawn(players);
        assertTrue(robot.posX == 0 && robot.posY == 0);
    }
}