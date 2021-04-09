package ui;

import model.Course;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// design for the panel that allows a user to enter and add courses to the course list
public class AddCourse extends JPanel {
    public CoursesField cf;
    private AddButton add;
    public static final JLabel dept = new JLabel("Department");
    public static final JLabel code = new JLabel("Code");
    public static final JLabel credits = new JLabel("Credits");
    private static final JTextField tf1 = new JTextField(5);
    private static final JTextField tf2 = new JTextField(5);
    private static final JTextField tf3 = new JTextField(5);

    // EFFECTS: creates that JPanel that allows a user to add courses, also initialized with a CoursesField to add
    //          courses to. There will be 3 text fields: department, course code and credits respectively, as well as
    //          an add button that allows the user to add the course to the CoursesField.
    public AddCourse(CoursesField cf) {
        super();
        this.cf = cf;
        add = new AddButton();
        this.add(dept);
        this.add(tf1);
        this.add(code);
        this.add(tf2);
        this.add(credits);
        this.add(tf3);
        this.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String department = tf1.getText();
                int courseCode = Integer.parseInt(tf2.getText());
                int courseCredits = Integer.parseInt(tf3.getText());
                Course c = new Course(department, courseCode, courseCredits);
                cf.add(c);
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
            }
        });
    }
}
