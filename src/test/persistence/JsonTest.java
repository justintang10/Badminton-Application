package persistence;


import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlayer(String name, int wins, int loss, Player player) {
        assertEquals(name, player.getName());
    }
}