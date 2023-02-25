package model;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList gamesPlayedIn;
    private int wins;
    private int loss;

    public Player(String name) {
        this.name = name;
        gamesPlayedIn = new ArrayList<>();
        wins = 0;
        loss = 0;
    }

    public String getName(){
        return name;
    }

    public int getWins(){
        return wins;
    }

    public int getLoss(){
        return loss;
    }

    public ArrayList getGamesPlayedIn() {
        return gamesPlayedIn;
    }

    public void addWins(){
        wins++;
    }

    public void addLoss(){
        loss++;
    }
}
