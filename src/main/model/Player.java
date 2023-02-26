package model;

// Represents a Player having a name, wins, and losses
public class Player {
    private String name; // Player name
    private int wins; // Tracks player wins
    private int loss; // Tracks player losses

    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: name on Player is set to playerName; wins and loss are
     * set to zero
     */
    public Player(String playerName) {
        this.name = playerName;
        wins = 0;
        loss = 0;
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
}
