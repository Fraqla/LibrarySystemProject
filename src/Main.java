public class Main {
    public static void main(String[] args) {
        // Initialize the library system
        Library library = new Library();

        // Borrowing a book
        System.out.println("Borrowing Book1:");
        library.borrowBook("user1", "Book1");  // Should succeed

        System.out.println("Borrowing Book2:");
        try {
            library.borrowBook("user1", "Book2");  // Should raise an assertion error (Book2 is out of stock)
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }

        // Returning a book
        System.out.println("Returning Book1:");
        library.returnBook("user1", "Book1");  // Should succeed

        // Attempting to return a book not borrowed by the user
        System.out.println("Attempting to return Book3 (not borrowed):");
        try {
            library.returnBook("user1", "Book3");  // Should raise an assertion error (Book3 not borrowed by user1)
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }

        // Checking availability of books
        System.out.println("Checking availability of Book1:");
        System.out.println("Book1 available: " + library.checkBookAvailability("Book1"));  // Should return true

        System.out.println("Checking availability of Book2:");
        System.out.println("Book2 available: " + library.checkBookAvailability("Book2"));  // Should return false
    }
}