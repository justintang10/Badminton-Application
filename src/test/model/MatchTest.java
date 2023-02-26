package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {

    private Match match1;

    @BeforeEach
    void setup() {
        match1 = new Match();
    }


    @Test
    void testAddPlayersTeamA() {
        Player player1 = new Player("justin");
        Player player2 = new Player("tang");
        Player player3 = new Player("dog");
        match1.addPlayersTeamA(player1);
        assertEquals(1, match1.getTeamASize());
        match1.addPlayersTeamB(player2);
        assertEquals(1, match1.getTeamBSize());
        match1.addPlayersTeamA(player3);
        assertEquals(2, match1.getTeamASize());
    }

    @Test
    void testAddPoint() {
        match1.addPoint(1);
        assertEquals(Arrays.asList(0, 1, 0, 0), match1.getTeamAPoints());
        match1.addPoint(2);
        assertEquals(Arrays.asList(0, 1, 0, 0), match1.getTeamBPoints());
        match1.addPoint(1);
        match1.addPoint(1);
        assertEquals(Arrays.asList(1, 3, 0, 0), match1.getTeamAPoints());
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        assertEquals(Arrays.asList(1, 0, 3, 0), match1.getTeamBPoints());
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(1);
        match1.addPoint(2);
        assertEquals(Arrays.asList(1, 3, 0, 2), match1.getTeamAPoints());
        assertEquals(Arrays.asList(1, 0, 3, 2), match1.getTeamBPoints());
        match1.addPoint(1);
        match1.addPoint(1);
        assertEquals(Arrays.asList(2, 3, 0, 4), match1.getTeamAPoints());
    }

    @SuppressWarnings("methodlength")
    @Test
    void testNextRound() {
        match1.addPoint(1);
        assertFalse(match1.nextround());
        match1.addPoint(1);
        match1.addPoint(1);
        assertTrue(match1.nextround());
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        assertFalse(match1.checkIfGameIsOver());
        assertTrue(match1.nextround());
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(2);
        assertFalse(match1.nextround());
        match1.addPoint(1);
        match1.addPoint(1);
        assertTrue(match1.nextround());
        assertEquals(match1.getTeamA(), match1.getMatchWinner());
        assertTrue(match1.checkIfGameIsOver());
    }
}
