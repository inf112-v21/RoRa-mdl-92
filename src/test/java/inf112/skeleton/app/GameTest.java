package inf112.skeleton.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {



    @Test
    public void HoleShouldKill(){
        Robot robot = new Robot(0, 2,null);
        Hole hole = new Hole(0,2);
        assertTrue(robot.Health==10);
        hole.fallInHole(robot);
        assertTrue(robot.Health==0);
    }

    @Test
    public void CogWheelLeftShouldRotate(){
        Robot robot = new Robot(0,2, null);
        CogWheelLeft cogwheelleft = new CogWheelLeft(0,2);
        cogwheelleft.DoAction(robot);
        assertNotSame(Direction.LEFT, robot.d );
    }

    @Test
    public void CogWheelRightShouldRotate(){
        Robot robot = new Robot(0,2,null);
        CogWheelRight cogwheelright = new CogWheelRight(0,2);
        cogwheelright.DoAction(robot);
        assertNotSame(Direction.LEFT, robot.d);
    }

}
