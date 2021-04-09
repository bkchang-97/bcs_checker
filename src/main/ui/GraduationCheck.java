package ui;

import model.CourseList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// design for the graduation check functionality
public class GraduationCheck extends JMenuItem {
    private CoursesField cf;

    // EFFECTS: creates the Graduation check menu item based on the courses from CoursesField cf
    public GraduationCheck(CoursesField cf) {
        super("Graduation");
        this.cf = cf;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cf.courses.meetsGraduationRequirements()) {
                    new PassCheck();
                } else {
                    new FailCheck();
                }
            }
        });
    }
}