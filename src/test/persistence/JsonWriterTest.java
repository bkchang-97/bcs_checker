package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Adapted from JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CourseList cl = new CourseList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            CourseList cl = new CourseList();
            JsonWriter writer = new JsonWriter("./data/testWriterCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            cl = reader.read();
            assertEquals(0, cl.numTaken());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            CourseList cl = new CourseList();
            cl.add(new Course("CPSC", 330,3));
            cl.add(new Course("CPSC", 210, 4));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourseList.json");
            cl = reader.read();
            List<Course> courses = cl.getCourses();
            assertEquals(2, courses.size());
            checkCourse("CPSC", 330, 3, courses.get(0));
            checkCourse("CPSC", 210, 4, courses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}