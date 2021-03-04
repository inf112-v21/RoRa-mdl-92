package inf112.skeleton.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

    @Test
    public void ShouldDoTurn() {
        Game game = new Game();
        game.DoTurn();
        assertEquals(1, game.turn);
    }



}
