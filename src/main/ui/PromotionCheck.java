package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// design for the graduation check functionality
public class PromotionCheck extends JMenuItem {
    private CoursesField cf;

    // EFFECTS: creates the Promotion check menu item based on the courses from CoursesField cf
    public PromotionCheck(CoursesField cf) {
        super("Promotion");
        this.cf = cf;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cf.courses.meetsPromotionRequirements()) {
                    new PassCheck();
                } else {
                    new FailCheck();
                }
            }
        });
    }
}
