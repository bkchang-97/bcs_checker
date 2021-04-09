package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Adapted from JsonSerializationDemo

public class JsonTest {
    protected void checkCourse(String department, int code, int credits, Course c) {
        assertEquals(department, c.getDept());
        assertEquals(code, c.getNum());
        assertEquals(credits, c.getCredits());
    }
}
