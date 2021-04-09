package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a course at UBC with its department, course code and number of credits
public class Course implements Writable {

    public static final Course CPSC110 = new Course("CPSC", 110, 4);
    public static final Course CPSC330 = new Course("CPSC", 330, 3);
    public static final Course DSCI100 = new Course("DSCI", 100, 3);
    public static final Course CPSC100 = new Course("CPSC", 100, 3);
    public static final Course CPSC121 = new Course("CPSC", 121, 4);
    public static final Course MATH180 = new Course("MATH", 180, 3);
    public static final Course STAT203 = new Course("STAT", 203, 3);
    public static final Course ENGL301 = new Course("ENGL", 301, 3);
    public static final Course CPSC210 = new Course("CPSC", 210, 4);
    public static final Course CPSC213 = new Course("CPSC", 213, 4);
    public static final Course CPSC221 = new Course("CPSC", 221, 4);
    public static final Course CPSC310 = new Course("CPSC", 310, 4);
    public static final Course CPSC313 = new Course("CPSC", 313, 3);
    public static final Course CPSC320 = new Course("CPSC", 320, 3);

    private String dept;
    private int num;
    private int credits;

    // EFFECTS: creates a course of the given department (dept), course number (num) and the number of
    //          credits it is worth (cred)
    public Course(String dept, int num, int cred) {
        this.dept = dept;
        this.num = num;
        credits = cred;
    }

    // EFFECTS: returns the 4 letter department the course is in
    public String getDept() {
        return dept;
    }

    // EFFECTS: returns the 3 digit course number
    public int getNum() {
        return num;
    }

    // EFFECTS: returns the number of credits the course is worth
    public int getCredits() {
        return credits;
    }

    // EFFECTS: creates a JSONObject of this
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dept", dept);
        json.put("code", num);
        json.put("credits", credits);
        return json;
    }
}
