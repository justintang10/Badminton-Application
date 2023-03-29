package ui;

import model.ListOfPlayer;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// GUI class is the graphic user interface of the Badminton app
// creates the interface with the buttons and fields needed to execute user stories

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JTextField textFieldName;
    private JButton addButton;
    private JButton buttonShowPlayers;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JLabel labelPlayers;
    private JLabel pictureLabel;
    private JButton buttonRemove;
    private static final String JSON_STORE = "./data/badmintonplayers.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ListOfPlayer listOfPlayer = new ListOfPlayer();

    // EFFECTS: creates the GUI with the mainPanel, text fields, buttons, picture, and initializes JSON components.
    public GUI() throws FileNotFoundException {
        setContentPane(mainPanel);
        setTitle("Badminton App");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        addButton();
        removeButton();
        loadButton();
        saveButton();
        showPlayerButton();


        ImageIcon image = new ImageIcon("/Users/justin/IdeaProjects/project_i0q5c/picture/badminton_cross.png");
        pictureLabel.setIcon(image);
    }

    // MODIFIES: this
    // EFFECTS: creates Action Listener to addButton and creates and adds player to listOfPlayer
    public void addButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = textFieldName.getText();
                listOfPlayer.addPlayerByName(firstname);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates Action Listener to loadButton and loads listOfPLayer from file when pressed
    public void loadButton() {
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listOfPlayer = jsonReader.read();
                    System.out.println("Loaded " + " from " + JSON_STORE);
                } catch (IOException a) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates Action Listener to saveButton and saves listOfPLayer to file when pressed
    public void saveButton() {
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(listOfPlayer);
                    jsonWriter.close();
                    System.out.println("Saved " + " to " + JSON_STORE);
                } catch (FileNotFoundException a) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates Action Listener to showPlayerButton and shows listOfPlayer
    public void showPlayerButton() {
        buttonShowPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder joined = new StringBuilder();
                for (String name : listOfPlayer.listOfPlayerNames()) {
                    joined.append(name + ", ");
                }
                labelPlayers.setText(String.valueOf(joined));
            }
        });
    }

    public void removeButton() {
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = textFieldName.getText();
                listOfPlayer.removePlayerByName(firstname);
            }
        });
    }



}
