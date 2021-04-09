package ui;

import javax.swing.*;
import java.awt.*;

// represents the actual course checking app
public class CourseCheckerApp extends JFrame {
    private CoursesField cf;
    private JMenuBar mb;
    private AddCourse cp;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    // EFFECTS: creates the JFrame for the application, with a MenuBar at the top, a CoursesField in the middle and
    //          the AddCourse panel at the bottom.
    public CourseCheckerApp() {
        super("BCS Course Checker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        cf = new CoursesField();
        cp = new AddCourse(cf);
        mb = new MenuBar(cf);
        this.getContentPane().add(BorderLayout.NORTH, mb);
        this.getContentPane().add(BorderLayout.SOUTH, cp);
        this.getContentPane().add(BorderLayout.CENTER, cf);
        this.setVisible(true);
    }

    // EFFECTS: runs the BCS Course Checker application
    public static void main(String [] args) {
        new CourseCheckerApp();
    }
}