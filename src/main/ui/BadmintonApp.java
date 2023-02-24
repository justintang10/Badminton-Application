package ui;

import model.ListOfPlayer;
import model.Player;

import java.util.Arrays;
import java.util.Scanner;

public class BadmintonApp {
    private Scanner input;
    private ListOfPlayer list;

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
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addPlayer();
        } else if (command.equals("g")) {
            getPlayersInSession();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        list = new ListOfPlayer();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void addPlayer(){
        System.out.println("Enter name of player");
        String name = input.next();
        list.add(name);

    }

    private void getPlayersInSession(){
        list.getPlayers();
    }
}
