class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int bookIdCounter = 0;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.bookId = generateBookId();
        totalBooks++;
        availableBooks++;
    }

    public static String generateBookId() {
        bookIdCounter++;
        return "BID" + String.format("%03d", bookIdCounter);
    }

    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("---------------------------");
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued; // Array to store book IDs
    private int bookCount;
    private static int memberIdCounter = 0;
    private static final int MAX_BOOKS_ISSUED = 5;

    public Member(String memberName) {
        this.memberName = memberName;
        this.memberId = generateMemberId();
        this.booksIssued = new String[MAX_BOOKS_ISSUED];
        this.bookCount = 0;
    }

    private static String generateMemberId() {
        memberIdCounter++;
        return "MID" + String.format("%03d", memberIdCounter);
    }

    public void borrowBook(Book book) {
        if (bookCount == MAX_BOOKS_ISSUED) {
            System.out.println(memberName + " has reached the maximum number of issued books.");
            return;
        }
        if (book.issueBook()) {
            booksIssued[bookCount] = book.getBookId();
            bookCount++;
            System.out.println(memberName + " borrowed " + book.getBookId() + " - " + book.getTitle());
        } else {
            System.out.println("Book " + book.getBookId() + " is not available.");
        }
    }

    public void returnBook(String bookId, Book[] libraryBooks) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                // Mark book as returned in the library
                for (Book b : libraryBooks) {
                    if (b.getBookId().equals(bookId)) {
                        b.returnBook();
                        break;
                    }
                }
                // Remove book from the issued list (shift array left)
                for (int j = i; j < bookCount - 1; j++) {
                    booksIssued[j] = booksIssued[j + 1];
                }
                booksIssued[bookCount - 1] = null;
                bookCount--;
                System.out.println(memberName + " returned " + bookId);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(memberName + " has not borrowed book " + bookId);
        }
    }

    public void displayMemberInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Member Name: " + memberName);
        System.out.print("Books Issued: ");
        if (bookCount == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.print(booksIssued[i] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}

public class Q2_3LP {
    public static void main(String[] args) {
        // Create Books
        Book[] libraryBooks = new Book[4];
        libraryBooks[0] = new Book("1984", "George Orwell");
        libraryBooks[1] = new Book("To Kill a Mockingbird", "Harper Lee");
        libraryBooks[2] = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        libraryBooks[3] = new Book("Moby Dick", "Herman Melville");

        // Create Members
        Member[] members = new Member[2];
        members[0] = new Member("John Doe");
        members[1] = new Member("Jane Smith");

        // Display initial library info
        System.out.println("--- Initial Library and Member Status ---");
        System.out.println("\nLibrary Books:");
        for (Book b : libraryBooks) b.displayBookInfo();
        System.out.println("Members Info:");
        for (Member m : members) m.displayMemberInfo();

        // John borrows two books
        System.out.println("\n--- Transactions: Borrowing ---");
        members[0].borrowBook(libraryBooks[0]);
        members[0].borrowBook(libraryBooks[1]);

        // Jane tries to borrow one already borrowed book and one available
        members[1].borrowBook(libraryBooks[1]); // Not available
        members[1].borrowBook(libraryBooks[2]);

        // Display info after borrowing
        System.out.println("\n--- Status After Borrowing ---");
        System.out.println("\nLibrary Books:");
        for (Book b : libraryBooks) b.displayBookInfo();
        System.out.println("Members Info:");
        for (Member m : members) m.displayMemberInfo();

        // John returns one book
        System.out.println("\n--- Transactions: Returning ---");
        members[0].returnBook("BID001", libraryBooks);

        // Display info after return
        System.out.println("\n--- Status After Returning ---");
        System.out.println("\nLibrary Books:");
        for (Book b : libraryBooks) b.displayBookInfo();
        System.out.println("Members Info:");
        for (Member m : members) m.displayMemberInfo();

        System.out.println("\n--- Final Library Statistics ---");
        System.out.println("Total books in library: " + Book.getTotalBooks());
        System.out.println("Available books for borrowing: " + Book.getAvailableBooks());
    }
}

