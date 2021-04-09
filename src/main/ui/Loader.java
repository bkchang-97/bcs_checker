package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// represents the menu item that allows the loading a saved course list functionality
public class Loader extends JMenuItem {
    public static final String JSON_STORE = "data/courselist.json";
    private CoursesField cf;
    private JsonReader jsonReader;

    // EFFECTS: creates Load menu item that can load the saved course list from file onto the CoursesField
    public Loader(CoursesField cf) {
        super("Load");
        this.cf = cf;
        jsonReader = new JsonReader(JSON_STORE);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cf.courses = jsonReader.read();
                    System.out.println("Loaded " + " from " + JSON_STORE);
                    cf.load();
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }
}
