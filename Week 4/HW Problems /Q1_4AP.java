/**
 * Q1_4AP.java
 * Problem 1: Movie Ticket Booking
 * This program demonstrates constructor overloading to create movie
 tickets
 * with varying levels of detail.
 */
public class Q1_4AP {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;
    // 1. Default constructor
    public Q1_4AP() {
        this("Unknown Movie", "N/A", 0, 0.0);
        System.out.println("-> Default constructor called.");
    }
    // 2. Constructor with movie name
    public Q1_4AP(String movieName) {
        this(movieName, "N/A", 0, 200.0);
        System.out.println("-> Movie name constructor called.");
    }
    // 3. Constructor with movie name and seat number
    public Q1_4AP(String movieName, int seatNumber) {
        this(movieName, "PVR Cinemas", seatNumber, 250.0);
        System.out.println("-> Movie and seat constructor called.");
    }
    // 4. Full constructor (the "main" constructor that others call)
    public Q1_4AP(String movieName, String theatreName, int seatNumber,
                  double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
        System.out.println("-> Full constructor called.");
    }
    // Method to display ticket details
    public void printTicket() {
        System.out.println("\n--- MOVIE TICKET ---");
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat: " + (seatNumber > 0 ? seatNumber :
                "Any"));
        System.out.printf("Price: $%.2f\n", price);
        System.out.println("--------------------");
    }
    public static void main(String[] args) {
        System.out.println("Creating tickets...");
        Q1_4AP ticket1 = new Q1_4AP(); // Default
        Q1_4AP ticket2 = new Q1_4AP("Dune: Part Two"); // Movie name
        Q1_4AP ticket3 = new Q1_4AP("The Matrix", 12); // Movie and seat
        Q1_4AP ticket4 = new Q1_4AP("Inception", "IMAX", 7, 350.0); //
        System.out.println("\nPrinting all tickets:");
        ticket1.printTicket();
        ticket2.printTicket();
        ticket3.printTicket();
        ticket4.printTicket();
    }
}