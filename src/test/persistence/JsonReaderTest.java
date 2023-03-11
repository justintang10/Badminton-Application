package persistence;

import model.ListOfPlayer;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfPlayer wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfPlayer() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfPlayer.json");
        try {
            ListOfPlayer wr = reader.read();
            assertEquals(0, wr.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderListOfPlayer() {
        JsonReader reader = new JsonReader("./data/testReaderListOfPlayer.json");
        try {
            ListOfPlayer wr = reader.read();
            List<Player> players = wr.getListOfPlayers();
            assertEquals(2, players.size());
            checkPlayer("justin", 0,0, players.get(0));
            checkPlayer("tang", 0,0, players.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}