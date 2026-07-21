import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a single student. A Student object keeps its own name, ID,
 * the list of courses it is enrolled in, and a map of the grades it has
 * been given in each of those courses.
 *
 * All instance variables are private; other classes may only read or
 * change them through the public getter/setter and behavior methods
 * below (encapsulation).
 */
public class Student {

    private String name;
    private String id;
    private final List<Course> enrolledCourses;   // courses this student is taking
    private final Map<Course, Double> grades;     // grade earned in each course

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    // ---------------- getters ----------------

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /** Returns an unmodifiable view of the courses this student is enrolled in. */
    public List<Course> getEnrolledCourses() {
        return List.copyOf(enrolledCourses);
    }

    // ---------------- setters ----------------

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name;
    }

    public void setId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("ID cannot be empty.");
        this.id = id;
    }

    // ---------------- behavior (instance methods that manipulate object state) ----------------

    /**
     * Enrolls this student in the given course by adding it to the
     * student's list of enrolled courses. Does not touch the Course's
     * own capacity bookkeeping; that is handled separately by
     * CourseManagement / Course so that capacity errors can be reported
     * to the caller.
     *
     * @param course the Course object to enroll in; must not be null.
     */
    public void enrollInCourse(Course course) {
        if (course == null)
            throw new IllegalArgumentException("Course cannot be null.");
        if (enrolledCourses.contains(course))
            throw new IllegalStateException(name + " is already enrolled in " + course.getCourseCode());
        enrolledCourses.add(course);
    }

    /**
     * Assigns (or updates) this student's grade for the given course.
     * The student must already be enrolled in the course.
     *
     * @param course the course the grade applies to
     * @param grade  numeric grade, expected to be in the range 0-100
     */
    public void assignGrade(Course course, double grade) {
        if (course == null)
            throw new IllegalArgumentException("Course cannot be null.");
        if (!enrolledCourses.contains(course))
            throw new IllegalStateException(name + " is not enrolled in " + course.getCourseCode());
        if (grade < 0 || grade > 100)
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        grades.put(course, grade);
    }

    /** Returns the grade for a specific course, or null if none has been assigned. */
    public Double getGrade(Course course) {
        return grades.get(course);
    }

    /** Returns an unmodifiable view of all grades this student has received. */
    public Map<Course, Double> getGrades() {
        return Map.copyOf(grades);
    }

    /**
     * Computes the average of all grades this student has been given so far.
     * Returns 0.0 if no grades have been recorded yet.
     */
    public double computeOverallGrade() {
        if (grades.isEmpty())
            return 0.0;
        double sum = 0;
        for (double g : grades.values())
            sum += g;
        return sum / grades.size();
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + name +
                ", coursesEnrolled=" + enrolledCourses.size() + "}";
    }
}
