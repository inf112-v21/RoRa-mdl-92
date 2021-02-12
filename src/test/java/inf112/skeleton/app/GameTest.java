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
            assertEquals(4, p.cardsList.size());
        }
    }

    @Test
    public void ShouldCompleteRegisters() {
        Game game = new Game();
        game.DoTurn();
        assertEquals(5, game.registerHistory.size());
        assertEquals(4, game.registerHistory.get(1).size());
    }
}
