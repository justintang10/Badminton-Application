package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Match {
    private static int nextMatchID = 1; // tracks id of next match created
    private int id; // match id
    private int game;
    private ArrayList<Integer> pointsTeamA;
    private ArrayList<Integer> pointsTeamB;
    private ArrayList teamA;
    private ArrayList teamB;

    public Match(){
        id = nextMatchID++;
        game = 1;
        pointsTeamA = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        pointsTeamB = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        teamA = new ArrayList<Player>();
        teamB = new ArrayList<Player>();
    }

    public void addPlayersTeamA(Player player) {
        teamA.add(player);
    }

    public void addPlayersTeamB(Player player) {
        teamB.add(player);
    }

    public void addPoint(int input) {
        if (input == 1) {
            pointsTeamA.set(game, (pointsTeamA.get(game) + 1));
        }
        if (input == 2) {
            pointsTeamB.set(game, (pointsTeamB.get(game) + 1));
        }
    }

    public ArrayList<Integer> getTeamAPoints() {
        return pointsTeamA;
    }

    public ArrayList<Integer> getTeamBPoints() {
        return pointsTeamB;
    }
}
