package inf112.skeleton.app;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class playCardTest {


    @Test
    public void TestPlayerCanPlayCard(){

        Player p = new Player(null);
        assertTrue(!p.canPlayCard());
        p.cardInputs.inputs.add(1);
        assertTrue(p.canPlayCard());
    }
}
