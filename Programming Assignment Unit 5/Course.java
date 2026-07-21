/**
 * Represents a single course offered by the university.
 *
 * Each Course object keeps track of its own code, name, maximum capacity,
 * and how many students are currently enrolled in it. In addition, the
 * class keeps a static (class-level) variable, totalEnrolledStudents, that
 * is shared by every Course object and records how many enrollments have
 * happened across ALL courses in the system. This demonstrates the
 * difference between instance state (belongs to one Course) and static
 * state (belongs to the Course class itself).
 */
public class Course {

    // ---- instance variables: one copy per Course object ----
    private final String courseCode;
    private final String courseName;
    private final int maxCapacity;
    private int currentEnrollment;   // how many students are in THIS course

    // ---- static variable: only one copy, shared by every Course ----
    private static int totalEnrolledStudents = 0;

    /**
     * Constructs a new Course.
     *
     * @param courseCode  short unique code, e.g. "CS1102"
     * @param courseName  descriptive name, e.g. "Intro to Programming"
     * @param maxCapacity maximum number of students allowed in the course
     */
    public Course(String courseCode, String courseName, int maxCapacity) {
        if (courseCode == null || courseCode.isBlank())
            throw new IllegalArgumentException("Course code cannot be empty.");
        if (courseName == null || courseName.isBlank())
            throw new IllegalArgumentException("Course name cannot be empty.");
        if (maxCapacity <= 0)
            throw new IllegalArgumentException("Maximum capacity must be positive.");

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    // ---------------- getters (read-only access to private fields) ----------------

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public boolean isFull() {
        return currentEnrollment >= maxCapacity;
    }

    /**
     * Called whenever a student is successfully enrolled in this course.
     * Increases both the course's own enrollment count AND the shared
     * static counter that tracks enrollments across all courses.
     *
     * @throws CourseFullException if the course has no open seats left.
     */
    void incrementEnrollment() throws CourseFullException {
        if (isFull()) {
            throw new CourseFullException(
                "Cannot enroll: course " + courseCode + " is at maximum capacity (" + maxCapacity + ").");
        }
        currentEnrollment++;
        totalEnrolledStudents++;   // update the shared, class-wide count
    }

    /**
     * Static method: returns the total number of enrolled students across
     * every Course instance that has ever been created. Because it is
     * static, it can be called as Course.getTotalEnrolledStudents(),
     * without needing any particular Course object.
     */
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName +
                " (" + currentEnrollment + "/" + maxCapacity + " enrolled)";
    }
}
