package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("justin");
    }

    @Test
    void testGetName() {
        assertEquals("justin", player.getName());
    }

    @Test
    void testWinsLossses() {
        assertEquals(0, player.getWins());
        assertEquals(0, player.getLoss());
        player.addLoss();
        player.addWins();
        assertEquals(1, player.getLoss());
        assertEquals(1, player.getWins());
    }
}
