package persistence;

import model.ListOfPlayer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Player;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfPlayer from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfPlayer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfPlayer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfPlayer from JSON object and returns it
    private ListOfPlayer parseListOfPlayer(JSONObject jsonObject) {
        ListOfPlayer lp = new ListOfPlayer();
        addPlayers(lp, jsonObject);
        return lp;
    }

    // MODIFIES: lp
    // EFFECTS: parses listofplayer from JSON object and adds them to ListOfPlayer
    private void addPlayers(ListOfPlayer lp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfPlayers");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(lp, nextPlayer);
        }
    }

    // MODIFIES: lp
    // EFFECTS: parses Player from JSON object and adds it to ListOfPlayer
    private void addPlayer(ListOfPlayer lp, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int wins = jsonObject.getInt("wins");
        int loss = jsonObject.getInt("loss");
        Player player = new Player(name, wins, loss);
        lp.addPlayer(player);
    }

}
