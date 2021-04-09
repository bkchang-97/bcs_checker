package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// represents a list of UBC courses
public class CourseList implements Writable {
    private static final int NUMCREDITSPROMOTION = 24;
    private static final int NUMCREDITSGRADUATION = 70;

    private List<Course> courses;

    public CourseList() {
        courses = new ArrayList<Course>();
    }

    // EFFECTS: returns the list of courses
    public List<Course> getCourses() {
        return courses;
    }

    // MODIFIES: this
    // EFFECTS: adds course c to the course list if it is not already in the course list
    public void add(Course c) {
        if (!taken(c)) {
            courses.add(c);
        }
    }

    // EFFECTS: return True if course c is in the courses list, and False otherwise
    public boolean taken(Course c) {
        for (Course next: courses) {
            if (c.getDept().equals(next.getDept()) && c.getNum() == next.getNum()) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of items in the course list
    public int numTaken() {
        return courses.size();
    }

    // EFFECTS: returns True if the course list meets the promotion requirements in BCS: has taken CPSC 110 and 121,
    //          has taken MATH 180 and STAT 203, has taken an ENGL course and has at least 24 credits
    public boolean meetsPromotionRequirements() {
        CourseList reqCourses = new CourseList();
        reqCourses.add(Course.CPSC110);
        reqCourses.add(Course.CPSC121);
        reqCourses.add(Course.MATH180);
        reqCourses.add(Course.STAT203);

        return (courseRequirement(reqCourses)
                && englishRequirement()
                && creditRequirement(NUMCREDITSPROMOTION));
    }
    // EFFECTS: returns True if the course list meets the graduation requirements in BCS: meets the promotion
    //          requirements, has taken CPSC 210, 213, 221, 310, 313 and 320, and has at least 70 credits

    public boolean meetsGraduationRequirements() {
        CourseList reqCourses = new CourseList();
        reqCourses.add(Course.CPSC210);
        reqCourses.add(Course.CPSC213);
        reqCourses.add(Course.CPSC221);
        reqCourses.add(Course.CPSC310);
        reqCourses.add(Course.CPSC313);
        reqCourses.add(Course.CPSC320);

        return (meetsPromotionRequirements()
                && courseRequirement(reqCourses)
                && creditRequirement(NUMCREDITSGRADUATION));
    }

    // EFFECTS: returns True if a course in the list is from the ENGL department, False otherwise
    protected boolean englishRequirement() {
        for (Course c: courses) {
            if (c.getDept().equals("ENGL")) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns True if courses contains all the courses in cl, False otherwise
    protected boolean courseRequirement(CourseList cl) {
        for (Course c: cl.courses) {
            if (!this.taken(c)) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: returns True if the sum of all the credits in courses is at least the value of reqCredits, False
    //          otherwise
    protected boolean creditRequirement(int reqCredits) {
        int acc = 0;
        for (Course c: courses) {
            acc += c.getCredits();
        }
        return acc >= reqCredits;
    }

    // EFFECTS: creates a JSON object of this
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns things in this courselist as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c: courses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
