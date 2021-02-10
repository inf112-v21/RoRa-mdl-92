package inf112.skeleton.app;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    public int turn=0;
    public List<Player> playerList = new ArrayList<>();
    Cards cards = new Cards();
    Map<Player, List<Integer>> registers = new HashMap<>();

    public void DoTurn() {
        turn += 1;
        if (turn == 1) {
            Player p1 = new Player();
            Player p2 = new Player();
            Player p3 = new Player();
            Player p4 = new Player();
            playerList.addAll(Arrays.asList(p1,p2,p3,p4));
        }
        for (Player p : playerList) {
            cards.DealCards(p);
            int index = 0;
            registers.put(p, p.ProgramRegisters());
        }
    }

    public String ShowPlayers() {
        String output = "";
        for (Player p : playerList) {
            output += "Player " + (playerList.indexOf(p)+1) + ": " + p.cardsList.toString() + '\n';
        }
        return output;
    }

}

//temp class Player
class Player {

    public List<Integer> cardsList = new ArrayList<>();
    public int damage=0;

    public List<Integer> ProgramRegisters() {
        List<Integer> registers = new ArrayList<>();
        for (int i=0; i<5; i++) {
            int randCard = ThreadLocalRandom.current().nextInt(0, 9);
            registers.add(cardsList.get(randCard));
            cardsList.remove(randCard);
        }
        return registers;
    }
}

//temp class Cards
class Cards {

    public void DealCards(Player player) {
        for (int i = 0; i < 9- player.damage; i++) {
            int randCard = ThreadLocalRandom.current().nextInt(1, 6);
            player.cardsList.add(randCard);
        }
    }

}
