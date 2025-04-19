import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library {

    private Map<String, Map<String, Object>> books = new HashMap<>();
    private Map<String, Map<String, Object>> users = new HashMap<>();

    public Library() {
        // Initializing books
        Map<String, Object> book1 = new HashMap<>();
        book1.put("available", 5);
        book1.put("borrowed_by", new ArrayList<String>());
        books.put("Book1", book1);

        Map<String, Object> book2 = new HashMap<>();
        book2.put("available", 0);  // No available copies
        book2.put("borrowed_by", new ArrayList<String>());
        books.put("Book2", book2);

        // Initializing users
        Map<String, Object> user1 = new HashMap<>();
        user1.put("borrowed_books", new ArrayList<String>());
        users.put("user1", user1);
    }

    // Borrowing a book
    public void borrowBook(String userId, String bookId) {
        assert users.containsKey(userId) : "User " + userId + " is not registered.";
        assert books.containsKey(bookId) : "Book " + bookId + " does not exist.";
        assert (int) books.get(bookId).get("available") > 0 : "Book " + bookId + " is not available.";

        // Invariant: Check that available copies are an integer
        assert books.get(bookId).get("available") instanceof Integer : "Available copies of " + bookId + " should be an integer.";

        // Process: Borrowing the book
        ArrayList<String> borrowedBy = (ArrayList<String>) books.get(bookId).get("borrowed_by");
        borrowedBy.add(userId);

        ArrayList<String> borrowedBooks = (ArrayList<String>) users.get(userId).get("borrowed_books");
        borrowedBooks.add(bookId);

        books.get(bookId).put("available", (int) books.get(bookId).get("available") - 1);  // Decrease availability

        // Post-condition: Ensure book availability is not negative
        assert (int) books.get(bookId).get("available") >= 0 : "Available copies of " + bookId + " should not be negative.";
        System.out.println(userId + " successfully borrowed " + bookId + ".");
    }

    // Returning a book
    public void returnBook(String userId, String bookId) {
        // Pre-conditions (assertions)
        assert users.containsKey(userId) : "User " + userId + " is not registered.";

    
        // Invariant: borrowed_books list must exist for the user
        ArrayList<String> borrowedBooks = (ArrayList<String>) users.get(userId).get("borrowed_books");
        assert borrowedBooks != null : "User " + userId + " has no borrowed books list.";
        assert borrowedBooks.contains(bookId) : "User " + userId + " did not borrow " + bookId + ".";
        
        assert books.containsKey(bookId) : "Book " + bookId + " does not exist.";
        // Process return
        borrowedBooks.remove(bookId);
        ArrayList<String> borrowedBy = (ArrayList<String>) books.get(bookId).get("borrowed_by");
        borrowedBy.remove(userId);
    
        // Update availability
        int newAvailable = (int) books.get(bookId).get("available") + 1;
        books.get(bookId).put("available", newAvailable);
    
        // Post-condition: Availability should not be negative
        assert newAvailable >= 0 : "Available copies of " + bookId + " became negative.";
        System.out.println(userId + " successfully returned " + bookId + ".");
    }
    // Checking book availability
    public boolean checkBookAvailability(String bookId) {
        assert books.containsKey(bookId) : "Book " + bookId + " does not exist.";

        // Invariant: The book availability should be checked accurately
        assert books.get(bookId).get("available") instanceof Integer : "Available copies of " + bookId + " should be an integer.";

        // Process: Checking availability
        return (int) books.get(bookId).get("available") > 0;
    }
}

