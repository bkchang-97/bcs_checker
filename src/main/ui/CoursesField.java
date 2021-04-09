package ui;

import model.Course;
import model.CourseList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

// design for the panel that displays the list of courses that have been added
public class CoursesField extends JPanel {
    public CourseList courses;
    public JList<String> list;
    public DefaultListModel<String> listModel = new DefaultListModel<String>();

    // EFFECTS: creates the JPanel for displaying the list of courses to the user.
    public CoursesField() {
        super();
        courses = new CourseList();
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.add(list);
    }

    // MODIFIES: this
    // EFFECTS: adds the Course c to the course list and also update the display accordingly
    public void add(Course c) {
        if (!courses.taken(c)) {
            courses.add(c);
            listModel.addElement(c.getDept() + " " + c.getNum());
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the course list from saved file
    public void load() {
        listModel.clear();
        for (Course c: courses.getCourses()) {
            listModel.addElement(c.getDept() + " " + c.getNum());
        }
    }
}
