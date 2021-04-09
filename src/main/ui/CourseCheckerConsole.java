package ui;

import model.Course;
import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// represents the course checker app but with interaction with the console, no GUI
public class CourseCheckerConsole {
    public static final String JSON_STORE = "data/courselist.json";
    private CourseList courses;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the BCSChecker application
    public CourseCheckerConsole() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCourseChecker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCourseChecker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for using the app!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addCourse();
        } else if (command.equals("v")) {
            viewCourses();
        } else if (command.equals("p")) {
            promotionCheck();
        } else if (command.equals("g")) {
            graduationCheck();
        } else if (command.equals("s")) {
            saveCourseList();
        } else if (command.equals("l")) {
            loadCourseList();
        } else {
            System.out.println("Invalid selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes courses
    private void init() {
        courses = new CourseList();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Course");
        System.out.println("\tv -> View Course List");
        System.out.println("\tp -> Check Promotion Eligibility");
        System.out.println("\tg -> Check Graduation Eligibility");
        System.out.println("\ts -> Save Coursework");
        System.out.println("\tl -> Load Coursework");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a course to the course list
    private void addCourse() {
        System.out.println("Enter 4-letter course department:");
        String dept = input.next();

        System.out.println("Enter the course cCode");
        int code = input.nextInt();

        System.out.println("Enter the number of credits");
        int credits = input.nextInt();

        Course c = new Course(dept, code, credits);
        courses.add(c);
    }

    // EFFECTS: displays all courses in the course list
    private void viewCourses() {
        for (Course c : courses.getCourses()) {
            String toPrint = c.getDept() + Integer.toString(c.getNum());
            System.out.println(toPrint);
        }
    }

    // EFFECTS: runs a check on the promotion requirements over the course list
    private void promotionCheck() {
        if (courses.meetsPromotionRequirements()) {
            System.out.println("You are eligible for promotion to Year 4");
        } else {
            System.out.println("You are missing promotion requirements");
        }
    }

    // EFFECTS: runs a check on the graduation requirements over the course list
    private void graduationCheck() {
        if (courses.meetsGraduationRequirements()) {
            System.out.println("Congrats, you can apply for graduation!");
        } else {
            System.out.println("It seems you are missing some graduation requirements");
        }
    }

    // EFFECTS: saves coursework to file
    private void saveCourseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(courses);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads courses from file
    private void loadCourseList() {
        try {
            courses = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
