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

    @Override
    public String toString(){
        return name;
    }
}
