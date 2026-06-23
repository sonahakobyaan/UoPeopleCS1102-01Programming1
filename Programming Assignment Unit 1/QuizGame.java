import java.util.Scanner;

/**
 * QuizGame.java
 * A simple multiple-choice quiz game with 5 questions.
 * The program tracks correct answers and displays a final score percentage.
 */
public class QuizGame {

    public static void main(String[] args) {

        // Scanner for reading user input
        Scanner scanner = new Scanner(System.in);

        // Variable to track the number of correct answers
        int score = 0;

        // Total number of questions
        int totalQuestions = 5;

        System.out.println("========================================");
        System.out.println("       Welcome to the Java Quiz Game!   ");
        System.out.println("========================================");
        System.out.println("Answer each question by typing A, B, C, or D.\n");

        // -------------------------------------------------------
        // Question 1: Java primitive types
        // -------------------------------------------------------
        System.out.println("Question 1: Which of the following is a primitive data type in Java?");
        System.out.println("  A. String");
        System.out.println("  B. Array");
        System.out.println("  C. int");
        System.out.println("  D. Scanner");
        System.out.print("Your answer: ");
        String answer1 = scanner.nextLine().trim().toUpperCase();

        // Validate input for question 1
        while (!answer1.equals("A") && !answer1.equals("B") &&
               !answer1.equals("C") && !answer1.equals("D")) {
            System.out.print("Invalid input. Please enter A, B, C, or D: ");
            answer1 = scanner.nextLine().trim().toUpperCase();
        }

        // Check answer using switch statement
        switch (answer1) {
            case "C":
                System.out.println("Correct!\n");
                score++;
                break;
            default:
                System.out.println("Incorrect. The correct answer is C (int).\n");
        }

        // -------------------------------------------------------
        // Question 2: System.out.println
        // -------------------------------------------------------
        System.out.println("Question 2: What does System.out.println() do in Java?");
        System.out.println("  A. Reads input from the user");
        System.out.println("  B. Declares a variable");
        System.out.println("  C. Prints text and moves to a new line");
        System.out.println("  D. Compiles the program");
        System.out.print("Your answer: ");
        String answer2 = scanner.nextLine().trim().toUpperCase();

        while (!answer2.equals("A") && !answer2.equals("B") &&
               !answer2.equals("C") && !answer2.equals("D")) {
            System.out.print("Invalid input. Please enter A, B, C, or D: ");
            answer2 = scanner.nextLine().trim().toUpperCase();
        }

        switch (answer2) {
            case "C":
                System.out.println("Correct!\n");
                score++;
                break;
            default:
                System.out.println("Incorrect. The correct answer is C.\n");
        }

        // -------------------------------------------------------
        // Question 3: Boolean values
        // -------------------------------------------------------
        System.out.println("Question 3: What are the two possible values of a boolean variable in Java?");
        System.out.println("  A. 0 and 1");
        System.out.println("  B. yes and no");
        System.out.println("  C. on and off");
        System.out.println("  D. true and false");
        System.out.print("Your answer: ");
        String answer3 = scanner.nextLine().trim().toUpperCase();

        while (!answer3.equals("A") && !answer3.equals("B") &&
               !answer3.equals("C") && !answer3.equals("D")) {
            System.out.print("Invalid input. Please enter A, B, C, or D: ");
            answer3 = scanner.nextLine().trim().toUpperCase();
        }

        switch (answer3) {
            case "D":
                System.out.println("Correct!\n");
                score++;
                break;
            default:
                System.out.println("Incorrect. The correct answer is D (true and false).\n");
        }

        // -------------------------------------------------------
        // Question 4: Operator precedence
        // -------------------------------------------------------
        System.out.println("Question 4: What is the result of the expression: 2 + 3 * 4 in Java?");
        System.out.println("  A. 20");
        System.out.println("  B. 14");
        System.out.println("  C. 24");
        System.out.println("  D. 10");
        System.out.print("Your answer: ");
        String answer4 = scanner.nextLine().trim().toUpperCase();

        while (!answer4.equals("A") && !answer4.equals("B") &&
               !answer4.equals("C") && !answer4.equals("D")) {
            System.out.print("Invalid input. Please enter A, B, C, or D: ");
            answer4 = scanner.nextLine().trim().toUpperCase();
        }

        // Using if statement to check answer
        if (answer4.equals("B")) {
            System.out.println("Correct! Multiplication has higher precedence than addition.\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is B (14).");
            System.out.println("Because * is evaluated before +: 3*4=12, then 2+12=14.\n");
        }

        // -------------------------------------------------------
        // Question 5: if-else vs switch
        // -------------------------------------------------------
        System.out.println("Question 5: Which conditional statement is best suited for checking");
        System.out.println("           a single variable against many fixed values?");
        System.out.println("  A. while loop");
        System.out.println("  B. for loop");
        System.out.println("  C. if-else");
        System.out.println("  D. switch-case");
        System.out.print("Your answer: ");
        String answer5 = scanner.nextLine().trim().toUpperCase();

        while (!answer5.equals("A") && !answer5.equals("B") &&
               !answer5.equals("C") && !answer5.equals("D")) {
            System.out.print("Invalid input. Please enter A, B, C, or D: ");
            answer5 = scanner.nextLine().trim().toUpperCase();
        }

        if (answer5.equals("D")) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is D (switch-case).\n");
        }

        // -------------------------------------------------------
        // Compute and display final score
        // -------------------------------------------------------
        double percentage = ((double) score / totalQuestions) * 100;

        System.out.println("========================================");
        System.out.println("              Quiz Complete!            ");
        System.out.println("========================================");
        System.out.println("You answered " + score + " out of " + totalQuestions + " questions correctly.");
        System.out.printf("Your final score: %.1f%%%n", percentage);

        // Display performance message using if-else
        if (percentage == 100) {
            System.out.println("Excellent! Perfect score!");
        } else if (percentage >= 80) {
            System.out.println("Great job!");
        } else if (percentage >= 60) {
            System.out.println("Good effort! Keep practicing.");
        } else {
            System.out.println("Keep studying — you'll do better next time!");
        }

        scanner.close();
    }
}