package model;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseListTest {

    private static final int NUMCREDITSPROMOTION = 24;
    private static final int NUMCREDITSGRADUATION = 70;

    private static final Course PROMDUMMY = new Course("DUMMY", 100, 20);
    private static final Course GRADDUMMY = new Course("DUMMY", 200, 65);
    private static final CourseList PROMOTIONCOURSES = new CourseList();
    private static final CourseList GRADUATIONCOURSES = new CourseList();
    private CourseList courses;

    @BeforeEach
    public void runBefore() {
        courses = new CourseList();
        PROMOTIONCOURSES.add(Course.CPSC110);
        PROMOTIONCOURSES.add(Course.CPSC121);
        PROMOTIONCOURSES.add(Course.MATH180);
        PROMOTIONCOURSES.add(Course.STAT203);

        GRADUATIONCOURSES.add(Course.CPSC210);
        GRADUATIONCOURSES.add(Course.CPSC213);
        GRADUATIONCOURSES.add(Course.CPSC221);
        GRADUATIONCOURSES.add(Course.CPSC310);
        GRADUATIONCOURSES.add(Course.CPSC313);
        GRADUATIONCOURSES.add(Course.CPSC320);

    }

    @Test
    public void testGetCourses() {
        List<Course> courseList = new ArrayList<Course>(
                Arrays.asList(Course.CPSC210, Course.CPSC330, Course.DSCI100)
        );
        courses.add(Course.CPSC210);
        courses.add(Course.CPSC330);
        courses.add(Course.DSCI100);

        assertEquals(courseList, courses.getCourses());
    }

    @Test
    public void testAdd() {
        courses.add(Course.CPSC110);
        assertTrue(courses.taken(Course.CPSC110));
        assertEquals(1, courses.numTaken());
    }

    @Test
    public void testAddAlreadyThere() {
        courses.add(Course.CPSC330);
        courses.add(Course.CPSC330);
        assertTrue(courses.taken(Course.CPSC330));
        assertEquals(1, courses.numTaken());

    }

    @Test
    public void testTaken() {
        courses.add(Course.CPSC100);
        courses.add(Course.CPSC110);
        courses.add(Course.CPSC330);
        assertTrue(courses.taken(Course.CPSC110));
        assertTrue(courses.taken(Course.CPSC330));
        assertFalse(courses.taken(Course.DSCI100));
    }

    @Test
    public void testNumTaken() {
        courses.add(Course.CPSC100);
        assertEquals(1, courses.numTaken());
        courses.add(Course.CPSC110);
        courses.add(Course.CPSC330);
        assertEquals(3, courses.numTaken());
    }

    @Test
    public void testMeetsPromotionRequirementsTrue() {
        courses.add(Course.CPSC110);
        courses.add(Course.CPSC121);
        courses.add(Course.MATH180);
        courses.add(Course.STAT203);
        assertFalse(courses.meetsPromotionRequirements());
        courses.add(Course.ENGL301);
        assertFalse(courses.meetsPromotionRequirements());
        courses.add(PROMDUMMY);
        assertTrue(courses.meetsPromotionRequirements());
    }

    @Test
    public void testMeetsPromotionRequirementsFailsCourseCheck() {
        courses.add(Course.ENGL301);
        assertFalse(courses.meetsPromotionRequirements());

        courses.add(PROMDUMMY);
        assertFalse(courses.meetsPromotionRequirements());
    }

    @Test
    public void testMeetsPromotionRequirementsFailsCourseEnglishCheck() {
        courses.add(Course.CPSC110);
        courses.add(PROMDUMMY);
        assertFalse(courses.meetsPromotionRequirements());
    }

    @Test
    public void testMeetsGraduationRequirementsTrue() {
        courses.add(Course.CPSC110);
        courses.add(Course.CPSC121);
        courses.add(Course.MATH180);
        courses.add(Course.STAT203);
        courses.add(Course.ENGL301);
        courses.add(PROMDUMMY);
        assertFalse(courses.meetsGraduationRequirements());

        courses.add(Course.CPSC210);
        courses.add(Course.CPSC213);
        courses.add(Course.CPSC221);
        courses.add(Course.CPSC310);
        courses.add(Course.CPSC313);
        courses.add(Course.CPSC320);
        assertFalse(courses.meetsGraduationRequirements());

        courses.add(GRADDUMMY);
        assertTrue(courses.meetsGraduationRequirements());
    }

    @Test
    public void testMeetsGraduationRequirementsFailsPromotionCheck() {
        courses.add(Course.CPSC210);
        courses.add(Course.CPSC213);
        courses.add(Course.CPSC221);
        courses.add(Course.CPSC310);
        courses.add(Course.CPSC313);
        courses.add(Course.CPSC320);
        assertFalse(courses.meetsGraduationRequirements());

        courses.add(GRADDUMMY);
        assertFalse(courses.meetsGraduationRequirements());
    }

    @Test
    public void testMeetsGraduationRequirementsFailsCoursePromotionCheck() {
        courses.add(PROMDUMMY);
        courses.add(GRADDUMMY);
        assertFalse(courses.meetsGraduationRequirements());
    }

    @Test
    public void testEnglishRequirement() {
        courses.add(Course.CPSC210);
        assertFalse(courses.englishRequirement());
        courses.add(Course.ENGL301);
        assertTrue(courses.englishRequirement());
    }

    @Test
    public void testCourseRequirementPromotion() {
        assertFalse(courses.courseRequirement(PROMOTIONCOURSES));
        courses.add(Course.CPSC110);
        courses.add(Course.CPSC121);
        assertFalse(courses.courseRequirement(PROMOTIONCOURSES));
        courses.add(Course.MATH180);
        courses.add(Course.STAT203);
        assertTrue(courses.courseRequirement(PROMOTIONCOURSES));
    }

    @Test
    public void testCreditRequirementPromotion() {
        courses.add(Course.CPSC110);
        courses.add(Course.CPSC121);
        courses.add(Course.STAT203);
        courses.add(Course.MATH180);
        assertFalse(courses.creditRequirement(NUMCREDITSPROMOTION));


    }
}