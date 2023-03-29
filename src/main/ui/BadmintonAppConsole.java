package ui;

import model.ListOfPlayer;
import model.Match;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// BadmintonAppConsole is the user interface side of the Badminton app in the terminal
// starts the main app loop, asks for user input when required, and prints the app objects onto the console

public class BadmintonAppConsole {
    private static final String JSON_STORE = "./data/badmintonplayers.json";
    private Scanner input;
    private ListOfPlayer listOfPlayer;
    private Match match;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the badminton application
    public BadmintonAppConsole() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBadmintonApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBadmintonApp() {
        boolean keepGoing = true;
        String command = null;
        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add players");
        System.out.println("\tg -> get players");
        System.out.println("\tw -> get player stat");
        System.out.println("\tb -> start match");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addPlayer();
        } else if (command.equals("g")) {
            getPlayersInSession();
        } else if (command.equals("w")) {
            getStats();
        } else if (command.equals("b")) {
            runMatch();
        } else if (command.equals("s")) {
            saveListOfPlayer();
        } else if (command.equals("l")) {
            loadListOfPlayer();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes listofPlayers and Scanner
    private void init() {
        listOfPlayer = new ListOfPlayer();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: creates a player based on the name inputted and adds player to listOfPlayer
    private void addPlayer() {
        System.out.println("Enter name of player");
        String name = input.next();
        listOfPlayer.addPlayerByName(name);
    }

    // EFFECT: prints out the names of listOfPlayer
    private void getPlayersInSession() {
        System.out.println(listOfPlayer.listOfPlayerNames());
    }

    // REQUIRES: input has to be a player that is in listOfPlayer
    // EFFECTS: prints out the stats of player in listOfPlayer
    private void getStats() {
        System.out.println("Which player?");
        System.out.println(listOfPlayer.listOfPlayerNames());
        String name = input.next();
        int index = listOfPlayer.getPlayerIndex(name);
        if (index != -1) {
            Player playerstat = listOfPlayer.getPlayer(name);
            System.out.println("Wins: " + playerstat.getWins());
            System.out.println("Losses: " + playerstat.getLoss());
        } else {
            System.out.println("Player entered does not exist");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input during a match
    private void runMatch() {
        boolean keepGoing = true;
        String command = null;
        newMatch();
        startMatch();
        while (keepGoing) {
            matchMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processMatchCommand(command);
                printScore();
                if (match.checkIfGameIsOver()) {
                    keepGoing = false;
                }
            }
        }
        match.winLoss();
        System.out.println("\nEnd of Game!");
        System.out.println("\nThe winner is " + match.getMatchWinner());
    }

    // EFFECTS: prints the score of the match
    private void printScore() {
        System.out.println("Score: ");
        System.out.println("Team A: " + match.getTeamAPoints());
        System.out.println("Team B: " + match.getTeamBPoints());
    }

    // EFFECTS: displays menu of options to user during a match
    private void matchMenu() {
        System.out.println("1 -> point for team A");
        System.out.println("2 -> point for team B");
        System.out.println("e -> end match");
    }

    // MODIFIES: this
    // EFFECTS: processes user command during a match
    private void processMatchCommand(String command) {
        if (command.equals("1")) {
            matchPoints(1);
        } else if (command.equals("2")) {
            matchPoints(2);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // REQUIRES: input be a input from the menu
    // MODIFIES: this
    // EFFECTS: adds the point to the team
    private void matchPoints(int input) {
        match.addPoint(input);
    }

    // EFFECTS: initializes a new match
    private void newMatch() {
        match = new Match();
    }

    // REQURIES:
    // EFFECTS: determines if its doubles or singles based off user commands
    private void startMatch() {
        System.out.println("singles or doubles?");
        String type = input.next();
        if (type.equals("doubles")) {
            chooseDoubles();
        } else if (type.equals("singles")) {
            chooseSingles();
        } else {
            System.out.println("Invalid type");
        }
    }

    // EFFECTS: initializes a double games player selection
    private void chooseDoubles() {
        newMatch();
        ArrayList<String> avaliablePlayers = listOfPlayer.listOfPlayerNames();
        for (int i = 0; i < 2; ++i) {
            System.out.println("Select players for team A");
            System.out.println("Available players:");
            System.out.println(avaliablePlayers);
            String player = input.next();
            match.addPlayersTeamA(listOfPlayer.getPlayer(player));
            avaliablePlayers.remove(avaliablePlayers.indexOf(player));
        }
        for (int i = 0; i < 2; ++i) {
            System.out.println("Select players for team B");
            System.out.println("Available players:");
            System.out.println(avaliablePlayers);
            String player = input.next();
            match.addPlayersTeamB(listOfPlayer.getPlayer(player));
            avaliablePlayers.remove(avaliablePlayers.indexOf(player));
        }
    }

    // EFFECTS: initializes a singles games player selection
    private void chooseSingles() {
        newMatch();
        ArrayList<String> avaliablePlayers = listOfPlayer.listOfPlayerNames();
        System.out.println("Select players for team A");
        System.out.println("Available players:");
        System.out.println(avaliablePlayers);
        String player = input.next();
        match.addPlayersTeamA(listOfPlayer.getPlayer(player));
        avaliablePlayers.remove(avaliablePlayers.indexOf(player));
        System.out.println("Select players for team B");
        System.out.println("Available players:");
        System.out.println(avaliablePlayers);
        player = input.next();
        match.addPlayersTeamB(listOfPlayer.getPlayer(player));
        avaliablePlayers.remove(avaliablePlayers.indexOf(player));
    }

    // EFFECTS: saves the ListOfPlayer to file
    private void saveListOfPlayer() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfPlayer);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ListOfPlayer from file
    private void loadListOfPlayer() {
        try {
            listOfPlayer = jsonReader.read();
            System.out.println("Loaded " +  " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
