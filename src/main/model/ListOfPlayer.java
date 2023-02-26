package model;

import java.util.ArrayList;

public class ListOfPlayer {
    private ArrayList<Player> players;

    public ListOfPlayer() {
        players = new ArrayList<Player>();
    }

    public void addPlayerByName(String name) {
        players.add(new Player(name));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<String> listOfPlayerNames() {
        ArrayList<String> aval = new ArrayList<String>();
        for (Player player : players) {
            aval.add(player.getName());
        }
        return aval;
    }

    public int getPlayerIndex(String name) {
        int position = -1;
        for (Player player : players) {
            if (player.getName().equals(name)) {
                position = players.indexOf(player);
            }
        }
        return position;
    }

    public Player getPlayer(String name) {
        return players.get(getPlayerIndex(name));
    }

    public int getSize() {
        return players.size();
    }
}
