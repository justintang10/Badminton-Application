package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Player having a name, wins, and losses
public class Player implements Writable {
    private String name; // Player name
    private int wins; // Tracks player wins
    private int loss; // Tracks player losses

    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: name on Player is set to playerName; wins and loss are
     * set to zero
     */
    public Player(String playerName, int wins, int loss) {
        this.name = playerName;
        this.wins = wins;
        this.loss = loss;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLoss() {
        return loss;
    }

    public void addWins() {
        wins++;
    }

    public void addLoss() {
        loss++;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("wins", wins);
        json.put("loss", loss);
        return json;
    }
}
