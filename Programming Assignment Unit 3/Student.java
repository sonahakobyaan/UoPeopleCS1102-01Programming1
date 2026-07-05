/**
 * The Student class represents a single student record in the university's
 * Student Record Management System.
 *
 * This class is a simple data container (sometimes called a "model" or
 * "entity" class). It follows the black-box principle discussed in the
 * course reading: all of its fields are private, and the only way for
 * outside code to read or change a student's information is through its
 * public methods (the getters and setters). This is known as
 * "encapsulation" -- it protects the internal state of a Student object
 * from being changed in unexpected or invalid ways.
 *
 * Each Student object is independent of every other Student object, which
 * is why name, id, age, and grade are declared as ordinary (non-static)
 * instance variables. Every Student that is created gets its own private
 * copy of these variables in memory (see Unit 3 reading, Section 4.2, and
 * the Neso Academy video on static vs. non-static members).
 *
 * @author  Student Record Management System
 */
public class Student {

    // ---------------------------------------------------------------
    // Private instance variables (the "state" of a Student object).
    // Each object created from this class gets its own copy of these
    // variables -- they are NOT shared between Student objects.
    // ---------------------------------------------------------------
    private int id;         // Unique identifier for the student
    private String name;    // Full name of the student
    private int age;        // Age of the student, in years
    private String grade;   // Current grade / year level of the student

    /**
     * Constructs a new Student with the given information.
     *
     * Precondition:  id must be a positive integer; name must not be null
     *                or empty; age must be a reasonable positive integer;
     *                grade must not be null or empty.
     * Postcondition: A new Student object is created whose fields hold the
     *                values passed in as parameters.
     *
     * @param id     the unique ID number of the student
     * @param name   the full name of the student
     * @param age    the age of the student
     * @param grade  the grade/year level of the student (e.g. "Freshman",
     *               "A", "Sophomore", etc.)
     */
    public Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // ---------------------------------------------------------------
    // Getter methods: these make up the "interface" that other classes
    // use to read a Student's private data, without needing to know how
    // that data is stored internally.
    // ---------------------------------------------------------------

    /** @return the student's unique ID number */
    public int getId() {
        return id;
    }

    /** @return the student's full name */
    public String getName() {
        return name;
    }

    /** @return the student's age */
    public int getAge() {
        return age;
    }

    /** @return the student's grade/year level */
    public String getGrade() {
        return grade;
    }

    // ---------------------------------------------------------------
    // Setter methods: these allow controlled updates to a Student's
    // private data. Because they are the ONLY way to change these
    // fields from outside the class, they give us a single place to
    // add validation if it is ever needed.
    // ---------------------------------------------------------------

    /** @param name the new name to give the student */
    public void setName(String name) {
        this.name = name;
    }

    /** @param age the new age to give the student */
    public void setAge(int age) {
        this.age = age;
    }

    /** @param grade the new grade/year level to give the student */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Returns a neatly formatted, human-readable summary of this
     * student's details. Used by the administrator interface whenever a
     * student record needs to be displayed on the screen.
     *
     * @return a formatted String containing the student's id, name,
     *         age, and grade
     */
    @Override
    public String toString() {
        return String.format("ID: %-6d Name: %-20s Age: %-4d Grade: %s",
                id, name, age, grade);
    }
}