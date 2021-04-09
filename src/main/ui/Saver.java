package ui;

import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// represents the menu item that allows the saving the current course list to file functionality
public class Saver extends JMenuItem {
    private CoursesField cf;
    private JsonWriter jsonWriter;
    public static final String JSON_STORE = "data/courselist.json";

    // EFFECTS: creates Save menu item that can save the course list from CoursesField cf to file
    public Saver(CoursesField cf) {
        super("Save");
        this.cf = cf;
        jsonWriter = new JsonWriter(JSON_STORE);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseList courses = cf.courses;
                try {
                    jsonWriter.open();
                    jsonWriter.write(courses);
                    jsonWriter.close();
                    System.out.println("Saved " + " to " + JSON_STORE);
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }
}
