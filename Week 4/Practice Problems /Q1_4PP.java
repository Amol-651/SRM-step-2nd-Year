public class Q1_4PP {
    String title;
    String author;
    double price;
    // Default constructor
    public Q1_4PP() {
        this.title = "Untitled";
        this.author = "Unknown Author";
        this.price = 0.0;
    }
    // Parameterized constructor
    public Q1_4PP(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    // Display method
    public void display() {
        System.out.println("--- Book Details ---");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.printf("Price: $%.2f\n", price);
    }
    public static void main(String[] args) {
// Create book1 using default constructor
        Q1_4PP book1 = new Q1_4PP();
// Create book2 using parameterized constructor
        Q1_4PP book2 = new Q1_4PP("The Lord of the Rings", "J.R.R. Tolkien", 25.99);
// Display both books
                System.out.println("Book created with default constructor:");
        book1.display();
        System.out.println("\nBook created with parameterized constructor:");
        book2.display();
    }
}