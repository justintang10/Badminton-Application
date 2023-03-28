package ui;

import model.ListOfPlayer;
import model.Match;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JTextField textFieldName;
    private JButton addButton;
    private JButton buttonShowPlayers;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JLabel labelPlayers;
    private JLabel pictureLabel;
    private static final String JSON_STORE = "./data/badmintonplayers.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ListOfPlayer listOfPlayer = new ListOfPlayer();

    public GUI() throws FileNotFoundException {
        setContentPane(mainPanel);
        setTitle("Badminton App");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String firstname = textFieldName.getText();
                    listOfPlayer.addPlayerByName(firstname);
            }
        });

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

        buttonShowPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder joined = new StringBuilder();
                for (String name: listOfPlayer.listOfPlayerNames()) {
                    joined.append(name + ", ");
                }
                labelPlayers.setText(String.valueOf(joined));
            }
        });

       ImageIcon image = new ImageIcon("/Users/justin/IdeaProjects/project_i0q5c/picture/badminton_cross.png");
        pictureLabel.setIcon(image);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
