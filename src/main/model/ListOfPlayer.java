package model;

import java.util.ArrayList;

public class ListOfPlayer {
    private ArrayList<Player> players;

    public ListOfPlayer() {
        players = new ArrayList<>();
    }

    public void add(String name) {
        players.add(new Player(name));
    }

    public void getPlayers(){
        for(Player player: players);
        System.out.println(players.toString());
    }
}
