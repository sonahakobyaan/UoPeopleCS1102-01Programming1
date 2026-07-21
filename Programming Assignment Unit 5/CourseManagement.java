import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseManagement is the central coordinator of the whole system.
 * It has no instances of its own (it is used purely through static
 * members) - it holds the master list of every Course that has been
 * created, the master list of every Student who has registered, and a
 * record of each student's most recently calculated overall grade.
 *
 * All of its fields are private static; the only way for the rest of
 * the program (the administrator interface) to read or change this
 * data is through the public static methods below.
 */
public class CourseManagement {

    private static final List<Course> courses = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    // Maps a student's ID to their most recently computed overall grade.
    private static final Map<String, Double> overallGrades = new HashMap<>();

    private CourseManagement() {
        // Prevents instantiation - this class is used only through static members.
    }

    // ---------------- course & student registries ----------------

    /**
     * Creates a new Course from the given information and adds it to the
     * master list of courses.
     */
    public static Course addCourse(String courseCode, String courseName, int maxCapacity) {
        Course course = new Course(courseCode, courseName, maxCapacity);
        courses.add(course);
        return course;
    }

    /** Registers a new student in the system. */
    public static Student addStudent(String name, String id) {
        Student student = new Student(name, id);
        students.add(student);
        return student;
    }

    public static List<Course> getCourses() {
        return List.copyOf(courses);
    }

    public static List<Student> getStudents() {
        return List.copyOf(students);
    }

    public static Course findCourseByCode(String courseCode) {
        for (Course c : courses)
            if (c.getCourseCode().equalsIgnoreCase(courseCode))
                return c;
        return null;
    }

    public static Student findStudentById(String id) {
        for (Student s : students)
            if (s.getId().equalsIgnoreCase(id))
                return s;
        return null;
    }

    // ---------------- enrollment / grading operations ----------------

    /**
     * Enrolls a student in a course. Delegates the actual bookkeeping to
     * Student.enrollInCourse() and Course.incrementEnrollment().
     *
     * @throws CourseFullException if the course is already at maximum capacity.
     */
    public static void enrollStudent(Student student, Course course) throws CourseFullException {
        if (student == null || course == null)
            throw new IllegalArgumentException("Student and course must not be null.");

        // Check capacity and update the course's (and the class-wide static)
        // enrollment counters first, so we don't leave the student half-enrolled
        // if the course turns out to be full.
        course.incrementEnrollment();
        student.enrollInCourse(course);
    }

    /** Assigns a grade to a student for a course, delegating to Student. */
    public static void assignGrade(Student student, Course course, double grade) {
        if (student == null || course == null)
            throw new IllegalArgumentException("Student and course must not be null.");
        student.assignGrade(course, grade);
    }

    /**
     * Calculates the overall (average) course grade for a student across
     * every course they have been graded in, stores it in the static
     * overallGrades map (keyed by student ID), and returns it.
     */
    public static double calculateOverallGrade(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Student must not be null.");
        double overall = student.computeOverallGrade();
        overallGrades.put(student.getId(), overall);
        return overall;
    }

    /** Static method: total enrollments across the whole system (delegates to Course). */
    public static int getTotalEnrolledStudents() {
        return Course.getTotalEnrolledStudents();
    }

    /** Returns the most recently calculated overall grade for a student ID, or null if none yet. */
    public static Double getStoredOverallGrade(String studentId) {
        return overallGrades.get(studentId);
    }
}
