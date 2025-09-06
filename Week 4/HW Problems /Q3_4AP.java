public class Q3_4AP {
    String title;
    String author;
    String isbn;
    boolean isAvailable;
    // Default constructor
    public Q3_4AP() {
        this.title = "No Title";
        this.author = "No Author";
        this.isbn = "N/A";
        this.isAvailable = false;
    }
    // Constructor with title and author
    public Q3_4AP(String title, String author) {
        this(title, author, "Not Specified", true);
    }
    // Full constructor
    public Q3_4AP(String title, String author, String isbn, boolean
            isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have successfully borrowed '" +
                    title + "'.");
        } else {
            System.out.println("Sorry, '" + title + "' is currently unavailable.");
        }
    }
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Thank you for returning '" + title +
                    "'.");
        } else {
            System.out.println("This book was not borrowed.");
        }
    }
    public void displayBookInfo() {
        System.out.println("\n--- Book Info ---");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Status: " + (isAvailable ? "Available" :
                "Checked Out"));
        System.out.println("-----------------");
    }
    public static void main(String[] args) {
        Q3_4AP book1 = new Q3_4AP("The Hobbit", "J.R.R. Tolkien");
        Q3_4AP book2 = new Q3_4AP("1984", "George Orwell", "978-0451524935", true);
        System.out.println("--- Initial Library Status ---");
        book1.displayBookInfo();
        book2.displayBookInfo();
        System.out.println("\n--- Transactions ---");
        book1.borrowBook();
        book2.borrowBook();
        book1.borrowBook(); // Should fail
        book1.returnBook();
        System.out.println("\n--- Final Library Status ---");
        book1.displayBookInfo();
        book2.displayBookInfo();
    }
}