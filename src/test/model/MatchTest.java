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
        ArrayList<Player> test = new ArrayList<>();
        test.add(player1);
        match1.addPlayersTeamA(player1);
        assertEquals(1, match1.getTeamASize());
        assertEquals(test, match1.getTeamA());
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
        match1.addPoint(1);
        assertEquals(Arrays.asList(1, 4, 0, 0), match1.getTeamAPoints());
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        assertEquals(Arrays.asList(1, 1, 4, 0), match1.getTeamBPoints());
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(2);
        assertEquals(Arrays.asList(1, 4, 0, 3), match1.getTeamAPoints());
        assertEquals(Arrays.asList(1, 1, 4, 3), match1.getTeamBPoints());
        match1.addPoint(1);
        match1.addPoint(1);
        assertEquals(Arrays.asList(2, 4, 0, 5), match1.getTeamAPoints());
    }

    @Test
    void testNextRound() {
        match1.addPoint(1);
        assertFalse(match1.nextRound());
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(2);
        assertFalse(match1.checkIfGameIsOver());
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(1);
        match1.addPoint(1);
        assertEquals(match1.getTeamA(), match1.getMatchWinner());
        assertTrue(match1.checkIfGameIsOver());
    }

    @Test
    void teamBWin() {
        Player player1 = new Player("justin");
        Player player2 = new Player("tang");
        match1.addPlayersTeamB(player1);
        match1.addPlayersTeamA(player2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        assertEquals(match1.getTeamB(),match1.getMatchWinner());
        match1.winLoss();
        assertEquals(1, player1.getWins());
        assertEquals(0, player2.getWins());
        assertEquals(0, player1.getLoss());
        assertEquals(1, player2.getLoss());
        assertTrue(match1.checkIfGameIsOver());
    }

    @Test
    void teamAWin() {
        Player player1 = new Player("justin");
        Player player2 = new Player("tang");
        match1.addPlayersTeamA(player2);
        match1.addPlayersTeamB(player1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.winLoss();
        assertEquals(match1.getTeamA(),match1.getMatchWinner());
        assertEquals(0, player1.getWins());
        assertEquals(1, player2.getWins());
        assertEquals(1, player1.getLoss());
        assertEquals(0, player2.getLoss());
        assertTrue(match1.checkIfGameIsOver());
    }

    @Test
    void testDueces() {
        Player player1 = new Player("justin");
        Player player2 = new Player("tang");
        match1.addPlayersTeamA(player1);
        match1.addPlayersTeamB(player2);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(1);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.addPoint(2);
        match1.winLoss();
        assertEquals(match1.getTeamB(),match1.getMatchWinner());
        assertEquals(0, player1.getWins());
        assertEquals(1, player2.getWins());
        assertEquals(1, player1.getLoss());
        assertEquals(0, player2.getLoss());
    }
}
