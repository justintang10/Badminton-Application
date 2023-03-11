package persistence;

import model.ListOfPlayer;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfPlayer wr = new ListOfPlayer();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfPlayer() {
        try {
            ListOfPlayer wr = new ListOfPlayer();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfPlayer.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfPlayer.json");
            wr = reader.read();
            assertEquals(0, wr.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterListOfPlayer() {
        try {
            ListOfPlayer wr = new ListOfPlayer();
            wr.addPlayer(new Player("justin", 0,0));
            wr.addPlayer(new Player("tang", 0,0));
            JsonWriter writer = new JsonWriter("./data/testWriterListOfPlayer.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterListOfPlayer.json");
            wr = reader.read();
            List<Player> players = wr.getListOfPlayers();
            assertEquals(2, players.size());
            checkPlayer("justin", 0,0, players.get(0));
            checkPlayer("tang", 0,0, players.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}