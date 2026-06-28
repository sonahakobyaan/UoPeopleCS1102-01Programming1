

import java.util.Scanner;


public class LibrarySystem {

    // Maximum number of different books the library can hold
    static final int MAX_BOOKS = 100;

    // Arrays to store book titles, authors, and quantities
    static String[] titles     = new String[MAX_BOOKS];
    static String[] authors    = new String[MAX_BOOKS];
    static int[]    quantities = new int[MAX_BOOKS];

    // Tracks how many different books are currently in the library
    static int bookCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("========================================");
        System.out.println("   Welcome to the Library System");
        System.out.println("========================================");

        // Main menu loop - continues until user selects Exit
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Handle invalid (non-integer) menu input
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            // Process the user's menu selection
            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> borrowBook(scanner);
                case 3 -> returnBook(scanner);
                case 4 -> {
                    System.out.println("Thank you for using the Library System. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please enter a number between 1 and 4.");
            }
        }
    }

    /**
     * Adds a new book to the library or updates the quantity
     * if a book with the same title AND author already exists.
     * If the same title exists but with a different author,
     * it is treated as a separate book entry.
     */
    static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        int quantity = 0;

        // Validate quantity input
        while (true) {
            System.out.print("Enter quantity to add: ");
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println("Quantity must be a positive number. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Search by both title AND author to avoid ambiguity
        int index = findBook(title, author);

        if (index != -1) {
            // Book with same title and author exists - update quantity
            quantities[index] += quantity;
            System.out.println("Book already exists. Updated quantity for \""
                    + title + "\" by " + author
                    + " to " + quantities[index] + ".");
        } else {
            // New book entry - add to library
            if (bookCount >= MAX_BOOKS) {
                System.out.println("Library is full. Cannot add more books.");
                return;
            }
            titles[bookCount]     = title;
            authors[bookCount]    = author;
            quantities[bookCount] = quantity;
            bookCount++;
            System.out.println("Book \"" + title + "\" by " + author
                    + " added successfully. Quantity: " + quantity + ".");
        }
    }

    /**
     * Allows a user to borrow a specified number of copies of a book.
     * Searches by both title and author to avoid ambiguity.
     */
    static void borrowBook(Scanner scanner) {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        // Search by both title and author
        int index = findBook(title, author);

        if (index == -1) {
            System.out.println("Error: \"" + title + "\" by " + author
                    + " was not found in the library.");
            return;
        }

        int requested = 0;

        // Validate requested quantity
        while (true) {
            System.out.print("Enter number of books to borrow: ");
            try {
                requested = Integer.parseInt(scanner.nextLine().trim());
                if (requested <= 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Check if enough copies are available
        if (requested > quantities[index]) {
            System.out.println("Error: Only " + quantities[index]
                    + " copy/copies of \"" + title + "\" by " + author
                    + " are available.");
        } else {
            quantities[index] -= requested;
            System.out.println("Success! You have borrowed " + requested
                    + " copy/copies of \"" + title + "\" by " + author
                    + ". Remaining copies: " + quantities[index] + ".");
        }
    }

    /**
     * Allows a user to return a specified number of copies of a book.
     * Searches by both title and author to verify library membership.
     */
    static void returnBook(Scanner scanner) {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        // Search by both title and author
        int index = findBook(title, author);

        if (index == -1) {
            System.out.println("Error: \"" + title + "\" by " + author
                    + " does not belong to this library system. Return rejected.");
            return;
        }

        int returned = 0;

        // Validate return quantity
        while (true) {
            System.out.print("Enter number of books to return: ");
            try {
                returned = Integer.parseInt(scanner.nextLine().trim());
                if (returned <= 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        quantities[index] += returned;
        System.out.println("Success! " + returned + " copy/copies of \""
                + title + "\" by " + author + " returned. Total copies now: "
                + quantities[index] + ".");
    }

    /**
     * Searches for a book by both title AND author (case-insensitive).
     * This prevents ambiguity when two books share the same title
     * but have different authors.
     * Returns the index if found, or -1 if not found.
     */
    static int findBook(String title, String author) {
        for (int i = 0; i < bookCount; i++) {
            if (titles[i].equalsIgnoreCase(title)
                    && authors[i].equalsIgnoreCase(author)) {
                return i;
            }
        }
        return -1;
    }
}