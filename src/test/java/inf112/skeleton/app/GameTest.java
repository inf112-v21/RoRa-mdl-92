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

    @Test
    public void ShouldDealCards() {
        Game game = new Game();
        game.DoTurn();
        for (Player p : game.playerList) {
            assertEquals(9, p.cardsList.size());
        }
    }

    @Test
    public void ShouldCompleteRegisters() {

    }
}
