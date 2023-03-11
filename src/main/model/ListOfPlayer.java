package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// ListOfPLayer represents the list of players that are in the current session
public class ListOfPlayer implements Writable {
    private ArrayList<Player> listOfPlayers; // List of players that are in the session

    // EFFECTS: creates an empty list of player
    public ListOfPlayer() {
        listOfPlayers = new ArrayList<Player>();
    }

    // REQUIRES: name has a non-zero length
    // MODIFIES: this
    // EFFECTS: creates a new player with name set to playerName and adds it to listOfPlayers
    public void addPlayerByName(String playerName) {
        listOfPlayers.add(new Player(playerName, 0, 0));
    }

    // REQUIRES: player
    // MODIFIES: this
    // EFFECTS: adds player to listOfPlayers
    public void addPlayer(Player player) {
        listOfPlayers.add(player);
    }


    // EFFECTS: Creates of a list of player names from listOfPlayers
    public ArrayList<String> listOfPlayerNames() {
        ArrayList<String> aval = new ArrayList<String>();
        for (Player player : listOfPlayers) {
            aval.add(player.getName());
        }
        return aval;
    }

    // REQUIRES: name has a non-zero length and is a player inside listOfPLayers
    // EFFECTS: returns the position of the player with name that equals playerName
    public int getPlayerIndex(String playerName) {
        int position = -1;
        for (Player player : listOfPlayers) {
            if (player.getName().equals(playerName)) {
                position = listOfPlayers.indexOf(player);
            }
        }
        return position;
    }

    // REQUIRES: name has a non-zero length and is a player inside listOfPLayers
    // EFFECTS: returns the player object with name that equals playerName
    public Player getPlayer(String playerName) {
        return listOfPlayers.get(getPlayerIndex(playerName));
    }

    public int getSize() {
        return listOfPlayers.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfPlayers", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : listOfPlayers) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
