package inf112.skeleton.app;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



public class playerInputTest {


    @Test
    public void stringToArrayTest(){
        playerInputs input = new playerInputs("1253");

        assertTrue(input.inputs.get(0) == 1);
        assertTrue(input.inputs.get(1) == 2);
        assertTrue(input.inputs.get(2) == 5);
        assertTrue(input.inputs.get(3) == 3);
    }

    @Test
    public void stringShutDownActionTest(){
        playerInputs input = new playerInputs("-");

        assertTrue(input.shutDown);
    }
}
