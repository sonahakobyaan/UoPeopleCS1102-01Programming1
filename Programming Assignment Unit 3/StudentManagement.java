import java.util.ArrayList;

/**
 * The StudentManagement class is responsible for storing and managing all
 * of the Student records in the system.
 *
 * Unlike the Student class, this class works with STATIC variables and
 * STATIC methods. That design choice is intentional and directly reflects
 * the ideas from Unit 3 (Static Subroutines and Static Variables):
 *
 *   - There is only ever ONE list of students and ONE student count for
 *     the whole running program -- it does not make sense for there to be
 *     a separate "list of all students" for every object, because the
 *     list itself represents information about the SYSTEM as a whole, not
 *     about any single student.
 *   - Because studentList and totalStudents are static, they belong to
 *     the class itself, are shared by the entire program, and can be
 *     accessed without ever creating a StudentManagement object (e.g.
 *     StudentManagement.addStudent(...)).
 *   - The static methods in this class (addStudent, updateStudent,
 *     viewStudent, viewAllStudents) can only work with other static
 *     members, which is exactly why studentList and totalStudents are
 *     also declared static.
 *
 * All fields are private, following the black-box / information-hiding
 * principle: the AdminInterface class does not need to know that an
 * ArrayList is used internally to store students -- it only needs to know
 * the public methods (the "contract") that this class provides.
 */
public class StudentManagement {

    // ---------------------------------------------------------------
    // Private static variables: shared by the entire class, not tied
    // to any individual object.
    // ---------------------------------------------------------------
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static int totalStudents = 0;

    /**
     * Adds a new student to the system.
     *
     * Precondition:  id must not already be in use by another student;
     *                age must be a positive number.
     * Postcondition: A new Student record has been created and added to
     *                studentList, and totalStudents has been incremented
     *                by one.
     *
     * @param id     the unique ID for the new student
     * @param name   the new student's name
     * @param age    the new student's age
     * @param grade  the new student's grade/year level
     * @throws IllegalArgumentException if a student with the given id
     *         already exists, or if age is not a positive number
     */
    public static void addStudent(int id, String name, int age, String grade) {
        if (findStudentById(id) != null) {
            throw new IllegalArgumentException(
                    "A student with ID " + id + " already exists.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException(
                    "Age must be a positive number.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student name cannot be empty.");
        }

        Student newStudent = new Student(id, name, age, grade);
        studentList.add(newStudent);
        totalStudents++;   // Static variable shared across the whole class
        System.out.println("Student added successfully. Total students: "
                + totalStudents);
    }

    /**
     * Updates the information of an existing student.
     *
     * Any parameter can be passed as null (for name/grade) or a negative
     * number (for age) to mean "leave this field unchanged."
     *
     * Precondition:  A student with the given id must already exist.
     * Postcondition: The matching Student's fields have been updated with
     *                the new values that were supplied.
     *
     * @param id       the ID of the student to update
     * @param newName  the new name, or null to leave the name unchanged
     * @param newAge   the new age, or a non-positive number to leave the
     *                 age unchanged
     * @param newGrade the new grade, or null to leave the grade unchanged
     * @throws IllegalArgumentException if no student with the given id
     *         exists in the system
     */
    public static void updateStudent(int id, String newName, int newAge, String newGrade) {
        Student student = findStudentById(id);
        if (student == null) {
            throw new IllegalArgumentException(
                    "No student found with ID " + id + ".");
        }

        if (newName != null && !newName.trim().isEmpty()) {
            student.setName(newName);
        }
        if (newAge > 0) {
            student.setAge(newAge);
        }
        if (newGrade != null && !newGrade.trim().isEmpty()) {
            student.setGrade(newGrade);
        }

        System.out.println("Student with ID " + id + " updated successfully.");
    }

    /**
     * Displays the details of a single student.
     *
     * Precondition:  A student with the given id must already exist.
     * Postcondition: The matching student's details have been printed to
     *                standard output.
     *
     * @param id the ID of the student whose details should be displayed
     * @throws IllegalArgumentException if no student with the given id
     *         exists in the system
     */
    public static void viewStudent(int id) {
        Student student = findStudentById(id);
        if (student == null) {
            throw new IllegalArgumentException(
                    "No student found with ID " + id + ".");
        }
        System.out.println(student);
    }

    /**
     * Displays the details of every student currently stored in the
     * system, along with the total number of students.
     *
     * Postcondition: All student records have been printed to standard
     *                output. If there are no students, a message saying
     *                so is printed instead.
     */
    public static void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("---- All Student Records (" + totalStudents + ") ----");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    /**
     * Returns the total number of students currently stored in the
     * system. This is an example of a static "getter" method -- it can
     * be called using the class name (StudentManagement.getTotalStudents())
     * without ever creating a StudentManagement object.
     *
     * @return the current value of totalStudents
     */
    public static int getTotalStudents() {
        return totalStudents;
    }

    /**
     * A private helper subroutine used internally by this class to search
     * for a student by ID. It is private because it is an implementation
     * detail -- other classes such as AdminInterface do not need to know
     * that a linear search of an ArrayList is being performed; they only
     * need the public methods above.
     *
     * @param id the ID to search for
     * @return the matching Student object, or null if no student with
     *         that id exists
     */
    private static Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}