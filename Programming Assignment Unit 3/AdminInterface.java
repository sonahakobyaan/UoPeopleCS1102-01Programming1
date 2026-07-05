import java.util.Scanner;

/**
 * AdminInterface is the entry point of the Student Record Management
 * System. It presents a text-based, menu-driven interface that an
 * administrator can use to add students, update student information,
 * and view student details.
 *
 * This class only ever calls the public static methods that are provided
 * by the StudentManagement class -- it never touches the private
 * studentList or totalStudents fields directly. This is another example
 * of the "black box" principle: AdminInterface only needs to know the
 * CONTRACT of StudentManagement (what it does), not its implementation
 * (how it stores the data).
 *
 * All user input is read using a single Scanner object, and every menu
 * option is wrapped in error handling so that invalid input (bad menu
 * choices, non-numeric IDs/ages, or an ID that does not exist) does not
 * crash the program.
 */
public class AdminInterface {

    // A single Scanner is shared by every method in this class so that
    // the console input stream is only opened once for the whole program.
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main method. Displays the menu in a loop until the
     * administrator chooses to exit the program.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println(" Welcome to the University Student Record Management System");
        System.out.println("=====================================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readMenuChoice();

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleUpdateStudent();
                    break;
                case 3:
                    handleViewStudent();
                    break;
                case 4:
                    StudentManagement.viewAllStudents();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            System.out.println(); // blank line for readability between operations
        }

        scanner.close();
    }

    /**
     * Prints the administrator menu options to the console.
     */
    private static void printMenu() {
        System.out.println("------------------- MAIN MENU -----------------------");
        System.out.println("1. Add a new student");
        System.out.println("2. Update student information");
        System.out.println("3. View a single student's details");
        System.out.println("4. View all students");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    /**
     * Safely reads the administrator's menu choice as an integer.
     *
     * Precondition:  None.
     * Postcondition: An integer has been read from standard input. If
     *                the administrator typed something that is not a
     *                valid integer, an error message is displayed and
     *                -1 is returned so that the calling code falls
     *                through to the "invalid choice" case.
     *
     * @return the menu choice entered by the administrator, or -1 if the
     *         input could not be parsed as an integer
     */
    private static int readMenuChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
            return -1;
        }
    }

    /**
     * Handles option 1 of the menu: prompts for and collects the details
     * of a new student, then passes them to StudentManagement.addStudent.
     * Any error thrown by addStudent (such as a duplicate ID) is caught
     * here and displayed to the administrator instead of crashing the
     * program.
     */
    private static void handleAddStudent() {
        System.out.println("--- Add New Student ---");
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter student age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter student grade/year level: ");
            String grade = scanner.nextLine().trim();

            StudentManagement.addStudent(id, name, age, grade);

        } catch (NumberFormatException e) {
            System.out.println("Error: ID and age must be whole numbers. Student was not added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles option 2 of the menu: prompts for a student ID and the new
     * values to apply, then passes them to StudentManagement.updateStudent.
     * The administrator may leave a field blank to keep it unchanged.
     */
    private static void handleUpdateStudent() {
        System.out.println("--- Update Student Information ---");
        try {
            System.out.print("Enter the ID of the student to update: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = null;
            }

            System.out.print("Enter new age (leave blank to keep current): ");
            String ageInput = scanner.nextLine().trim();
            int age = ageInput.isEmpty() ? -1 : Integer.parseInt(ageInput);

            System.out.print("Enter new grade/year level (leave blank to keep current): ");
            String grade = scanner.nextLine().trim();
            if (grade.isEmpty()) {
                grade = null;
            }

            StudentManagement.updateStudent(id, name, age, grade);

        } catch (NumberFormatException e) {
            System.out.println("Error: ID and age must be whole numbers. Student was not updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles option 3 of the menu: prompts for a student ID and displays
     * that student's details, or an error message if the ID does not
     * exist.
     */
    private static void handleViewStudent() {
        System.out.println("--- View Student Details ---");
        try {
            System.out.print("Enter the ID of the student to view: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            StudentManagement.viewStudent(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a whole number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}