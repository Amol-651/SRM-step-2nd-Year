/**
 * Q5_3AP.java
 * Assignment 5: Library Management System with Fine Calculation
 * A real-world application demonstrating business logic for a library,
 * managing books, members, and overdue fines.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Q5_3AP {

    // --- Nested Book Class ---
    // By placing Book inside Q5_3AP, it becomes Q5_3AP.Book and avoids conflicts.
    static class Book {
        String bookId;
        String title;
        String author;
        String isbn;
        String category;
        boolean isIssued;
        String issueDate; // Using String for simplicity, e.g., "Day 1"
        String dueDate;

        private static int bookIdCounter = 0;

        public Book(String title, String author, String isbn, String category) {
            this.bookId = "B" + String.format("%04d", ++bookIdCounter);
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.category = category;
            this.isIssued = false;
        }

        public String getBookId() { return bookId; }
        public String getTitle() { return title; }

        public void issueBook(int currentDay, int loanPeriod) {
            this.isIssued = true;
            this.issueDate = "Day " + currentDay;
            this.dueDate = "Day " + (currentDay + loanPeriod);
        }

        public void returnBook() {
            this.isIssued = false;
            this.issueDate = null;
            this.dueDate = null;
        }

        public void display() {
            System.out.printf("ID: %s | Title: %s | Author: %s | Issued: %s | Due: %s\n",
                    bookId, title, author, isIssued ? "Yes" : "No", isIssued ? dueDate : "N/A");
        }
    }

    // --- Nested Member Class ---
    // This is now Q5_3AP.Member
    static class Member {
        String memberId;
        String memberName;
        String memberType; // "Student", "Faculty", "General"
        List<Book> booksIssued;
        double totalFines;
        String membershipDate;

        private static int memberIdCounter = 0;

        public Member(String memberName, String memberType, int currentDay) {
            this.memberId = "M" + String.format("%03d", ++memberIdCounter);
            this.memberName = memberName;
            this.memberType = memberType;
            this.booksIssued = new ArrayList<>();
            this.totalFines = 0.0;
            this.membershipDate = "Day " + currentDay;
        }

        public void issueBook(Book book) { booksIssued.add(book); }
        public void returnBook(Book book) { booksIssued.remove(book); }
        public void addFine(double fine) { totalFines += fine; }
        public String getMemberType() { return memberType; }

        public void display() {
            System.out.printf("ID: %s | Name: %s | Type: %s | Books Issued: %d | Fines: $%.2f\n",
                    memberId, memberName, memberType, booksIssued.size(), totalFines);
        }
    }

    // --- Library Static Members and Methods ---
    private static String libraryName = "City Central Library";
    private static int totalBooks = 0;
    private static int totalMembers = 0;
    private static double finePerDay = 0.50;

    private static final int MAX_BOOKS_STUDENT = 3;
    private static final int LOAN_PERIOD_STUDENT = 14;
    private static final int MAX_BOOKS_FACULTY = 5;
    private static final int LOAN_PERIOD_FACULTY = 30;
    private static final int MAX_BOOKS_GENERAL = 2;
    private static final int LOAN_PERIOD_GENERAL = 10;

    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static int currentDay = 1;

    public static void issueBook(Member member, Book book) {
        int maxBooksAllowed;
        int loanPeriod;

        switch (member.getMemberType()) {
            case "Student":
                maxBooksAllowed = MAX_BOOKS_STUDENT;
                loanPeriod = LOAN_PERIOD_STUDENT;
                break;
            case "Faculty":
                maxBooksAllowed = MAX_BOOKS_FACULTY;
                loanPeriod = LOAN_PERIOD_FACULTY;
                break;
            default:
                maxBooksAllowed = MAX_BOOKS_GENERAL;
                loanPeriod = LOAN_PERIOD_GENERAL;
                break;
        }

        System.out.printf("\nAttempting to issue '%s' to %s...\n", book.getTitle(), member.memberName);

        if (book.isIssued) {
            System.out.println("Result: FAILED. Book is already issued.");
            return;
        }
        if (member.booksIssued.size() >= maxBooksAllowed) {
            System.out.println("Result: FAILED. Member has reached borrowing limit of " + maxBooksAllowed + " books.");
            return;
        }

        book.issueBook(currentDay, loanPeriod);
        member.issueBook(book);
        System.out.println("Result: SUCCESS. Book due on " + book.dueDate + ".");
    }

    public static double calculateFine(Book book) {
        if (!book.isIssued || book.dueDate == null) return 0.0;
        int dueDateDay = Integer.parseInt(book.dueDate.replace("Day ", ""));
        int overdueDays = currentDay - dueDateDay;
        return (overdueDays > 0) ? overdueDays * finePerDay : 0.0;
    }

    public static void returnBook(Member member, Book book) {
        System.out.printf("\nAttempting to return '%s' from %s...\n", book.getTitle(), member.memberName);
        if (!member.booksIssued.contains(book)) {
            System.out.println("Result: FAILED. Member has not issued this book.");
            return;
        }

        double fine = calculateFine(book);
        if (fine > 0) {
            member.addFine(fine);
            System.out.printf("Book is overdue. Fine of $%.2f added.\n", fine);
        }

        book.returnBook();
        member.returnBook(book);
        System.out.println("Result: SUCCESS. Book returned.");
    }

    public static List<Book> getOverdueBooks() {
        return books.stream()
                .filter(b -> b.isIssued && calculateFine(b) > 0)
                .collect(Collectors.toList());
    }

    public static void generateLibraryReport() {
        System.out.println("\n========= " + libraryName + " Report (Day " + currentDay + ") =========");
        System.out.println("Total Books in Collection: " + totalBooks);
        System.out.println("Total Registered Members: " + totalMembers);

        System.out.println("\n--- Overdue Books ---");
        List<Book> overdue = getOverdueBooks();
        if (overdue.isEmpty()) {
            System.out.println("None.");
        } else {
            overdue.forEach(Book::display);
        }
        System.out.println("=============================================");
    }

    public static void main(String[] args) {
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0618640157", "Fantasy"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "978-1503290563", "Romance"));
        books.add(new Book("1984", "George Orwell", "978-0451524935", "Dystopian"));
        totalBooks = books.size();

        members.add(new Member("Alice Smith", "Student", currentDay));
        members.add(new Member("Dr. Bob Johnson", "Faculty", currentDay));
        totalMembers = members.size();

        generateLibraryReport();

        Member alice = members.get(0);
        Book lotr = books.get(0);

        issueBook(alice, lotr);

        System.out.println("\n--- State at end of Day 1 ---");
        alice.display();
        lotr.display();

        currentDay = 20;
        System.out.println("\n...Time passes. It is now Day " + currentDay + "...");

        generateLibraryReport();

        returnBook(alice, lotr);

        System.out.println("\n--- Final State ---");
        alice.display();
        lotr.display();
    }
}

