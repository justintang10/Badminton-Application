package model;

import java.util.ArrayList;
import java.util.Arrays;

// Match preforms the major match tasks for the Badminton App by creating a match setting,
// performing match actions, and keeping track of the match state
public class Match {
    private int game; // tracks which game the match is currently at
    private ArrayList<Integer> pointsTeamA; // points of Team A
    private ArrayList<Integer> pointsTeamB; // points of Team B
    private ArrayList<Player> teamA; // list of players on Team A
    private ArrayList<Player> teamB; // list of players on Team B
    private final int winningPoint; // points needed to win a game
    private final int deuces; // points needed to induce a deuces

    // EFFECTS: Creates a new match with the amount of points needed to win a game, points to induce a deuce,
    //          game tracker, zero points of both teams, and an empty list of players for both teams
    public Match() {
        winningPoint = 4;
        deuces = winningPoint - 1;
        game = 1;
        pointsTeamA = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        pointsTeamB = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();
    }

    public int getTeamASize() {
        return teamA.size();
    }

    public int getTeamBSize() {
        return teamB.size();
    }

    // REQUIRES: player has to be a player that exist in ListOfPLayer
    // MODIFIES: this
    // EFFECTS: adds the player object into TeamA
    public void addPlayersTeamA(Player player) {
        teamA.add(player);
    }

    // REQUIRES: player has to be a player that exist in ListOfPLayer
    // MODIFIES: this
    // EFFECTS: adds the player object into TeamB
    public void addPlayersTeamB(Player player) {
        teamB.add(player);
    }

    public ArrayList<Player> getTeamA() {
        return teamA;
    }

    public ArrayList<Player> getTeamB() {
        return teamB;
    }

    // REQUIRES: input has to be an integer and either 1 or 2
    // MODIFIES: this
    // EFFECTS: increase the points of the team that depending on the input. Also checks if either team's point
    //          in the current game is at winningPoint, if so then the next game will start
    public void addPoint(int input) {
        if (input == 1) {
            pointsTeamA.set(game, (pointsTeamA.get(game) + 1));
            if (nextRound()) {
                game++;
            }
        }
        if (input == 2) {
            pointsTeamB.set(game, (pointsTeamB.get(game) + 1));
            if (nextRound()) {
                game++;
            }
        }
    }


    // EFFECTS: checks if either team is at winning points or if it is deuces. Returns true if either team is at
    //          the winning point, and then the next game score tracking begins
    public boolean nextRound() {
        if ((pointsTeamA.get(game) >= deuces && pointsTeamB.get(game) >= deuces)
                && ((pointsTeamA.get(game) - (pointsTeamB.get(game)) == 2)
                || (pointsTeamB.get(game) - (pointsTeamA.get(game)) == 2))) {
            winnerGame();
            return true;
        } else if ((pointsTeamA.get(game) >= deuces && pointsTeamB.get(game) >= deuces)
                && ((pointsTeamA.get(game) - (pointsTeamB.get(game)) < 2)
                || (pointsTeamB.get(game) - (pointsTeamA.get(game)) < 2))) {
            return false;
        } else if (pointsTeamB.get(game) == winningPoint) {
            pointsTeamB.set(0, (pointsTeamB.get(0) + 1));
            return true;
        } else if (pointsTeamA.get(game) == winningPoint) {
            pointsTeamA.set(0, (pointsTeamA.get(0) + 1));
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: determines the winners of the match and returns the winning team
    public ArrayList<Player> getMatchWinner() {
        if (pointsTeamA.get(0) == 2) {
            return teamA;
        } else {
            return teamB;
        }
    }

    // MODIFIES: player in the current match
    // EFFECTS: adds a win to each of the winning player's object, adds a loss to each of the losing player's object
    public void winLoss() {
        if (pointsTeamA.get(0) == 2) {
            for (Player player : teamA) {
                player.addWins();
            }
            for (Player player : teamB) {
                player.addLoss();
            }
        } else {
            for (Player player : teamB) {
                player.addWins();
            }
            for (Player player : teamA) {
                player.addLoss();
            }
        }
    }

    public ArrayList<Integer> getTeamAPoints() {
        return pointsTeamA;
    }

    public ArrayList<Integer> getTeamBPoints() {
        return pointsTeamB;
    }

    // EFFECTS: checks if the game is over by checking if either team has won two games
    public boolean checkIfGameIsOver() {
        if (pointsTeamA.get(0) == 2 || pointsTeamB.get(0) == 2) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: determines the winner in a duece situation and adds the game won to winning team
    public void winnerGame() {
        if (pointsTeamB.get(game) > pointsTeamA.get(game)) {
            pointsTeamB.set(0, (pointsTeamB.get(0) + 1));
        } else {
            pointsTeamA.set(0, (pointsTeamA.get(0) + 1));
        }
    }
}
