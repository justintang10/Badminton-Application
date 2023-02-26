package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Match {
    private static int nextMatchID = 1; // tracks id of next match created
    private int game;
    private ArrayList<Integer> pointsTeamA;
    private ArrayList<Integer> pointsTeamB;
    private ArrayList<Player> teamA;
    private ArrayList<Player> teamB;
    private final int winningPoint;
    private final int deuces;

    public Match() {
        winningPoint = 3;
        deuces = winningPoint - 1;
        game = 1;
        pointsTeamA = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        pointsTeamB = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();
    }
    public int getTeamASize() {return teamA.size();}

    public int getTeamBSize() {return teamB.size();}

    public void addPlayersTeamA(Player player) {
        teamA.add(player);
    }

    public void addPlayersTeamB(Player player) {
        teamB.add(player);
    }

    public ArrayList<Player> getTeamA() {
        return teamA;
    }


    public void addPoint(int input) {
        if (input == 1) {
            pointsTeamA.set(game, (pointsTeamA.get(game) + 1));
            if (nextround()) {
                game++;
            }
        }
        if (input == 2) {
            pointsTeamB.set(game, (pointsTeamB.get(game) + 1));
            if (nextround()) {
                game++;
            }
        }
    }

    public boolean nextround() {
        if ((pointsTeamA.get(game) >= deuces && pointsTeamB.get(game) >= deuces)
                && ((pointsTeamA.get(game) - (pointsTeamB.get(game)) == 2)
                || (pointsTeamB.get(game) - (pointsTeamA.get(game)) == 2))) {
            winnerGame();
            return true;
        } else if ((pointsTeamA.get(game) >= deuces && pointsTeamB.get(game) >= deuces)
                && ((pointsTeamA.get(game) - (pointsTeamB.get(game)) != 2)
                        || (pointsTeamB.get(game) - (pointsTeamA.get(game)) != 2))) {
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

    public ArrayList<Player> getMatchWinner() {
        if (pointsTeamA.get(0) == 2) {
            return teamA;
        } else {
            return teamB;
        }
    }

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
            for (Player player : teamB) {
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

    public boolean checkIfGameIsOver() {
        if (pointsTeamA.get(0) == 2 || pointsTeamB.get(0) == 2) {
            return true;
        } else {
            return false;
        }
    }

    public void winnerGame() {
        if (pointsTeamB.get(game) > pointsTeamA.get(game)) {
            pointsTeamB.set(0, (pointsTeamB.get(0) + 1));
        } else {
            pointsTeamA.set(0, (pointsTeamA.get(0) + 1));
        }
    }
}
