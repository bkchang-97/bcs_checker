package ui;

import model.CourseList;

import javax.swing.*;

// design for the menu bar at the top of the course checker app
public class MenuBar extends JMenuBar {
    private CoursesField cf;

    // EFFECTS: creates the MenuBar which allows user to save/load courses and perform promotion/graduation checks
    public MenuBar(CoursesField cf) {
        super();
        this.cf = cf;
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Check");

        menu1.add(new Saver(cf));
        menu1.add(new Loader(cf));
        menu2.add(new PromotionCheck(cf));
        menu2.add(new GraduationCheck(cf));

        this.add(menu1);
        this.add(menu2);
    }

}
