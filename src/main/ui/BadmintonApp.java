package ui;

import model.ListOfPlayer;
import model.Match;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class BadmintonApp {
    private Scanner input;
    private ListOfPlayer list;
    private Match match;

    // EFFECTS: runs the teller application
    public BadmintonApp() {
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
        System.out.println("\ts -> get player stat");
        System.out.println("\tb -> start match");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addPlayer();
        } else if (command.equals("g")) {
            getPlayersInSession();
        } else if (command.equals("s")) {
            getStats();
        } else if (command.equals("b")) {
            runMatch();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes ListofPlayers
    private void init() {
        list = new ListOfPlayer();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void addPlayer() {
        System.out.println("Enter name of player");
        String name = input.next();
        list.addPlayerByName(name);
    }

    private void getPlayersInSession() {
        System.out.println(list.listOfPlayerNames());
    }

    private void getStats() {
        System.out.println("Which player?");
        System.out.println(list.listOfPlayerNames());
        String name = input.next();
        int index = list.getPlayerIndex(name);
        if (index != -1) {
            Player playerstat = list.getPlayer(name);
            System.out.println("Wins: " + playerstat.getWins());
            System.out.println("Losses: " + playerstat.getLoss());
        } else {
            System.out.println("Player entered does not exist");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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

    private void printScore() {
        System.out.println("Score: ");
        System.out.println("Team A: " + match.getTeamAPoints());
        System.out.println("Team B: " + match.getTeamBPoints());
    }

    private void matchMenu() {
        System.out.println("1 -> point for team A");
        System.out.println("2 -> point for team B");
        System.out.println("e -> end match");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processMatchCommand(String command) {
        if (command.equals("1")) {
            matchPoints(1);
        } else if (command.equals("2")) {
            matchPoints(2);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void matchPoints(int input) {
        match.addPoint(input);
    }

    private void newMatch() {
        match = new Match();
    }

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

    private void chooseDoubles() {
        newMatch();
        ArrayList<String> avaliablePlayers = list.listOfPlayerNames();
        for (int i = 0; i < 2; ++i) {
            System.out.println("Select players for team A");
            System.out.println("Available players:");
            System.out.println(avaliablePlayers);
            String player = input.next();
            match.addPlayersTeamA(list.getPlayer(player));
            avaliablePlayers.remove(avaliablePlayers.indexOf(player));
        }
        for (int i = 0; i < 2; ++i) {
            System.out.println("Select players for team B");
            System.out.println("Available players:");
            System.out.println(avaliablePlayers);
            String player = input.next();
            match.addPlayersTeamB(list.getPlayer(player));
            avaliablePlayers.remove(avaliablePlayers.indexOf(player));
        }
    }

    private void chooseSingles() {
        newMatch();
        ArrayList<String> avaliablePlayers = list.listOfPlayerNames();
        System.out.println("Select players for team A");
        System.out.println("Available players:");
        System.out.println(avaliablePlayers);
        String player = input.next();
        match.addPlayersTeamA(list.getPlayer(player));
        avaliablePlayers.remove(avaliablePlayers.indexOf(player));
        System.out.println("Select players for team B");
        System.out.println("Available players:");
        System.out.println(avaliablePlayers);
        player = input.next();
        match.addPlayersTeamB(list.getPlayer(player));
        avaliablePlayers.remove(avaliablePlayers.indexOf(player));
    }
}
