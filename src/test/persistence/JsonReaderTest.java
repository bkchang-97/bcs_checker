package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Adapted from JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CourseList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourseList.json");
        try {
            CourseList cl = reader.read();
            assertEquals(0, cl.numTaken());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourseList.json");
        try {
            CourseList cl = reader.read();
            List<Course> courses = cl.getCourses();
            assertEquals(2, courses.size());
            checkCourse("CPSC", 330, 3, courses.get(0));
            checkCourse("CPSC", 210, 4, courses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}