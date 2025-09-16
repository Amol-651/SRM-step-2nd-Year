import java.util.*;

public class Q4_5LP {
    public static void main(String[] args) {
        System.out.println("=== Q4: Library Encapsulation Demo ===");
        Library lib = new Library("Nabha Central");
        Book b1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        Book b2 = new Book("978-0201633610", "Design Patterns", "Gamma et al.");
        lib.addBook(b1);
        lib.addBook(b2);
        lib.lendBook("978-0134685991", "Asha");
        System.out.println(lib.findBookByISBN("978-0134685991"));
        lib.returnBook("978-0134685991");
        System.out.println("Available books: " + lib.listAvailableBooks());
    }

    static final class Book {
        private final String isbn;
        private final String title;
        private final String author;
        private boolean onLoan;

        public Book(String isbn, String title, String author) {
            if (isbn == null || isbn.isEmpty()) throw new IllegalArgumentException("Bad ISBN");
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.onLoan = false;
        }

        public String getIsbn() { return isbn; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isOnLoan() { return onLoan; }

        private void setOnLoan(boolean val) { this.onLoan = val; } // controlled mutator

        @Override
        public String toString() {
            return title + " by " + author + (onLoan ? " (on loan)" : " (available)");
        }
    }

    static class Library {
        private final String name;
        private final Map<String, Book> catalog; // isbn -> book
        private final Map<String, String> loanRecords; // isbn -> borrower

        public Library(String name) {
            this.name = name;
            this.catalog = new HashMap<>();
            this.loanRecords = new HashMap<>();
        }

        public String getName() { return name; }

        public void addBook(Book b) {
            catalog.put(b.getIsbn(), b);
        }

        public boolean lendBook(String isbn, String borrower) {
            Book b = catalog.get(isbn);
            if (b == null) throw new IllegalArgumentException("No such book");
            if (b.isOnLoan()) {
                System.out.println("Already on loan.");
                return false;
            }
            b.setOnLoan(true);
            loanRecords.put(isbn, borrower);
            System.out.println("Lent " + b.getTitle() + " to " + borrower);
            return true;
        }

        public boolean returnBook(String isbn) {
            Book b = catalog.get(isbn);
            if (b == null) return false;
            if (!b.isOnLoan()) return false;
            b.setOnLoan(false);
            loanRecords.remove(isbn);
            System.out.println("Returned " + b.getTitle());
            return true;
        }

        public Book findBookByISBN(String isbn) {
            return catalog.get(isbn);
        }

        public List<String> listAvailableBooks() {
            List<String> res = new ArrayList<>();
            for (Book b : catalog.values()) {
                if (!b.isOnLoan()) res.add(b.toString());
            }
            return res;
        }
    }
}
