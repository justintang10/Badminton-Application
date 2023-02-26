package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListOfPlayerTest {

    private ListOfPlayer listOfPlayer;

    @BeforeEach
    void setup() {
        listOfPlayer = new ListOfPlayer();
    }

    @Test
    void testAdd() {
        listOfPlayer.addPlayerByName("k");
        listOfPlayer.addPlayerByName("m");
        assertEquals(2, listOfPlayer.getSize());
    }

    @Test
    void testlistOfPlayerNames() {
        listOfPlayer.addPlayerByName("k");
        listOfPlayer.addPlayerByName("m");
        ArrayList<String> test = new ArrayList<>();
        test.add("k");
        test.add("m");
        assertEquals(test, listOfPlayer.listOfPlayerNames());
    }

    @Test
    void testGetPlayerIndex() {
        listOfPlayer.addPlayerByName("tang");
        listOfPlayer.addPlayerByName("justin");
        listOfPlayer.addPlayerByName("cpsc");
        assertEquals(0, listOfPlayer.getPlayerIndex("tang"));
        assertEquals(2, listOfPlayer.getPlayerIndex("cpsc"));
        assertEquals(1, listOfPlayer.getPlayerIndex("justin"));
        assertEquals(-1, listOfPlayer.getPlayerIndex("ubc"));
    }

    @Test
    void testGetPlayer() {
        Player player1 = new Player("justin");
        Player player2 = new Player("tang");
        listOfPlayer.addPlayer(player1);
        listOfPlayer.addPlayer(player2);
        assertEquals(player1, listOfPlayer.getPlayer("justin"));
        assertEquals(player2, listOfPlayer.getPlayer("tang"));
    }

    @Test
    void testGetSize() {
        listOfPlayer.addPlayerByName("a");
        listOfPlayer.addPlayerByName("b");
        assertEquals(2, listOfPlayer.getSize());
    }


}