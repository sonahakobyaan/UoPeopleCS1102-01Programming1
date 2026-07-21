import java.util.List;
import java.util.Scanner;

/**
 * Interactive command-line interface for administrators of the Course
 * Enrollment and Grade Management System. Displays a menu, reads input,
 * validates it, and calls the appropriate static methods in
 * CourseManagement (which in turn call the appropriate instance methods
 * in Student and Course).
 */
public class AdminInterface {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println(" Course Enrollment and Grade Management System");
        System.out.println("=========================================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> addCourse();
                case "3" -> enrollStudent();
                case "4" -> assignGrade();
                case "5" -> calculateOverallGrade();
                case "6" -> viewAllStudents();
                case "7" -> viewAllCourses();
                case "8" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please enter a number from 1 to 8.\n");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Register a new student");
        System.out.println("2. Add a new course");
        System.out.println("3. Enroll a student in a course");
        System.out.println("4. Assign a grade");
        System.out.println("5. Calculate a student's overall grade");
        System.out.println("6. View all students");
        System.out.println("7. View all courses");
        System.out.println("8. Exit");
        System.out.println("---------------------------------------------------------");
        System.out.print("Enter your choice (1-8): ");
    }

    // ---------------- menu actions ----------------

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        try {
            if (CourseManagement.findStudentById(id) != null) {
                System.out.println("Error: a student with ID \"" + id + "\" already exists.\n");
                return;
            }
            Student s = CourseManagement.addStudent(name, id);
            System.out.println("Registered new student: " + s + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void addCourse() {
        System.out.print("Enter course code (e.g., CS1102): ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter maximum capacity: ");

        try {
            if (CourseManagement.findCourseByCode(code) != null) {
                System.out.println("Error: a course with code \"" + code + "\" already exists.\n");
                return;
            }
            int capacity = Integer.parseInt(scanner.nextLine().trim());
            Course c = CourseManagement.addCourse(code, name, capacity);
            System.out.println("Added new course: " + c + "\n");
        } catch (NumberFormatException e) {
            System.out.println("Error: maximum capacity must be a whole number.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void enrollStudent() {
        Student s = promptForStudent();
        if (s == null) return;
        Course c = promptForCourse();
        if (c == null) return;

        try {
            CourseManagement.enrollStudent(s, c);
            System.out.println(s.getName() + " was successfully enrolled in " + c.getCourseCode() + ".\n");
        } catch (CourseFullException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void assignGrade() {
        Student s = promptForStudent();
        if (s == null) return;
        Course c = promptForCourse();
        if (c == null) return;

        System.out.print("Enter grade for " + s.getName() + " in " + c.getCourseCode() + " (0-100): ");
        try {
            double grade = Double.parseDouble(scanner.nextLine().trim());
            CourseManagement.assignGrade(s, c, grade);
            System.out.println("Grade recorded.\n");
        } catch (NumberFormatException e) {
            System.out.println("Error: grade must be a number.\n");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void calculateOverallGrade() {
        Student s = promptForStudent();
        if (s == null) return;

        double overall = CourseManagement.calculateOverallGrade(s);
        System.out.printf("%s's overall course grade is %.2f%n%n", s.getName(), overall);
    }

    private static void viewAllStudents() {
        List<Student> students = CourseManagement.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered yet.\n");
            return;
        }
        System.out.println("Registered students:");
        for (Student s : students) {
            System.out.println("  " + s + ", enrolled in: " + courseCodes(s));
        }
        System.out.println();
    }

    private static void viewAllCourses() {
        List<Course> courses = CourseManagement.getCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses have been added yet.\n");
            return;
        }
        System.out.println("Available courses:");
        for (Course c : courses) {
            System.out.println("  " + c);
        }
        System.out.println("Total enrollments across all courses (static count): "
                + CourseManagement.getTotalEnrolledStudents() + "\n");
    }

    // ---------------- helpers ----------------

    private static String courseCodes(Student s) {
        StringBuilder sb = new StringBuilder();
        for (Course c : s.getEnrolledCourses()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(c.getCourseCode());
        }
        return sb.isEmpty() ? "(none)" : sb.toString();
    }

    private static Student promptForStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        Student s = CourseManagement.findStudentById(id);
        if (s == null) {
            System.out.println("Error: no student found with ID \"" + id + "\".\n");
        }
        return s;
    }

    private static Course promptForCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine().trim();
        Course c = CourseManagement.findCourseByCode(code);
        if (c == null) {
            System.out.println("Error: no course found with code \"" + code + "\".\n");
        }
        return c;
    }
}
