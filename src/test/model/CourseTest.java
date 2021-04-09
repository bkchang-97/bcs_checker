package model;

import model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    public void testGetDep() {
        assertEquals("CPSC", Course.CPSC110.getDept());
        assertEquals("DSCI", Course.DSCI100.getDept());
    }

    @Test
    public void testGetNum() {
        assertEquals(110, Course.CPSC110.getNum());
        assertEquals(330, Course.CPSC330.getNum());
        assertEquals(100, Course.DSCI100.getNum());
        assertEquals(100, Course.CPSC100.getNum());
        assertEquals(Course.DSCI100.getNum(), Course.CPSC100.getNum());
    }

    @Test
    public void testGetCredits() {
        assertEquals(4, Course.CPSC110.getCredits());
        assertEquals(3, Course.DSCI100.getCredits());
    }
}
