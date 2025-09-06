/**
 * Q3_3AP.java
 * Assignment 3: Hotel Reservation System
 * This program models a hotel system with multiple interacting classes:
 * Room, Guest, and Booking, to manage reservations.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Room {
    String roomNumber;
    String roomType; // e.g., "Single", "Double", "Suite"
    double pricePerNight;
    boolean isAvailable;
    int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
        this.maxOccupancy = maxOccupancy;
    }

    public void bookRoom() { isAvailable = false; }
    public void releaseRoom() { isAvailable = true; }
    public String getRoomType() { return roomType; }

    public void display() {
        System.out.printf("Room %s (%s) - $%.2f/night, Occupancy: %d, Available: %s\n",
                roomNumber, roomType, pricePerNight, maxOccupancy, isAvailable ? "Yes" : "No");
    }
}

class Guest {
    String guestId;
    String guestName;
    String phoneNumber;
    String email;
    List<String> bookingHistory; // List of booking IDs

    private static int guestIdCounter = 0;

    public Guest(String guestName, String phoneNumber, String email) {
        this.guestId = "G" + String.format("%03d", ++guestIdCounter);
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new ArrayList<>();
    }

    public void addBookingToHistory(String bookingId) {
        bookingHistory.add(bookingId);
    }

    public void display() {
        System.out.printf("Guest: %s (ID: %s), Phone: %s, History: %d bookings\n",
                guestName, guestId, phoneNumber, bookingHistory.size());
    }
}

class Booking {
    String bookingId;
    Guest guest;
    Room room;
    String checkInDate;
    String checkOutDate;
    double totalAmount;
    int numberOfNights;

    public Booking(Guest guest, Room room, String checkInDate, String checkOutDate, int nights) {
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = nights;
        this.totalAmount = calculateBill();
    }

    public double calculateBill() {
        return room.pricePerNight * numberOfNights;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void display() {
        System.out.println("\n--- Booking Details ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Guest: " + guest.guestName + " (ID: " + guest.guestId + ")");
        System.out.println("Room: " + room.roomNumber + " (" + room.roomType + ")");
        System.out.println("Period: " + checkInDate + " to " + checkOutDate + " (" + numberOfNights + " nights)");
        System.out.printf("Total Amount: $%.2f\n", totalAmount);
        System.out.println("----------------------");
    }
}

public class Q3_3AP {
    // --- Static variables representing the Hotel's state ---
    private static int totalBookings = 0;
    private static double hotelRevenue = 0;
    private static String hotelName = "The Grand OOP Hotel";

    private static List<Room> rooms = new ArrayList<>();
    private static List<Guest> guests = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static int bookingIdCounter = 0;

    // --- Reservation Management Methods (Static, part of the Hotel system) ---
    public static Booking makeReservation(Guest guest, String roomType, int nights, String checkInDate, String checkOutDate) {
        Room availableRoom = null;
        for (Room r : rooms) {
            if (r.roomType.equalsIgnoreCase(roomType) && r.isAvailable) {
                availableRoom = r;
                break;
            }
        }

        if (availableRoom == null) {
            System.out.println("Reservation failed: No available rooms of type '" + roomType + "'.");
            return null;
        }

        System.out.println("Making reservation for " + guest.guestName + " in a " + roomType + " room.");
        Booking newBooking = new Booking(guest, availableRoom, checkInDate, checkOutDate, nights);
        String bookingId = "B" + String.format("%03d", ++bookingIdCounter);
        newBooking.setBookingId(bookingId);

        availableRoom.bookRoom();
        guest.addBookingToHistory(bookingId);
        bookings.add(newBooking);
        totalBookings++;
        hotelRevenue += newBooking.totalAmount;

        System.out.println("Reservation successful! Room " + availableRoom.roomNumber + " booked. Booking ID: " + bookingId);
        return newBooking;
    }

    public static void cancelReservation(String bookingId) {
        Booking bookingToCancel = null;
        for (Booking b : bookings) {
            if (b.bookingId.equals(bookingId)) {
                bookingToCancel = b;
                break;
            }
        }

        if (bookingToCancel != null) {
            System.out.println("\nCancelling booking " + bookingId + "...");
            bookingToCancel.room.releaseRoom();
            hotelRevenue -= bookingToCancel.totalAmount;
            totalBookings--;
            bookings.remove(bookingToCancel);
            System.out.println("Booking " + bookingId + " cancelled successfully.");
        } else {
            System.out.println("Cancellation failed: Booking ID not found.");
        }
    }

    public static void checkAvailability() {
        System.out.println("\n--- Current Room Availability ---");
        for (Room r : rooms) {
            r.display();
        }
        System.out.println("-------------------------------------");
    }

    // --- Static Reporting Methods ---
    public static double getOccupancyRate() {
        if (rooms.isEmpty()) return 0.0;
        long occupiedRooms = rooms.stream().filter(r -> !r.isAvailable).count();
        return (double) occupiedRooms / rooms.size() * 100;
    }

    public static String getMostPopularRoomType() {
        if (bookings.isEmpty()) return "N/A";
        return bookings.stream()
                .collect(Collectors.groupingBy(b -> b.room.getRoomType(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("N/A");
    }

    public static void generateHotelReport() {
        System.out.println("\n====== " + hotelName + " Report ======");
        System.out.println("Total Rooms: " + rooms.size());
        System.out.println("Total Guests Registered: " + guests.size());
        System.out.println("Current Active Bookings: " + totalBookings);
        System.out.printf("Total Revenue to Date: $%.2f\n", hotelRevenue);
        System.out.printf("Current Occupancy Rate: %.2f%%\n", getOccupancyRate());
        System.out.println("Most Popular Room Type: " + getMostPopularRoomType());
        System.out.println("======================================");
    }

    public static void main(String[] args) {
        rooms.add(new Room("101", "Single", 100, 1));
        rooms.add(new Room("102", "Single", 100, 1));
        rooms.add(new Room("201", "Double", 150, 2));
        rooms.add(new Room("202", "Double", 150, 2));
        rooms.add(new Room("301", "Suite", 300, 4));

        Guest alice = new Guest("Alice Wonderland", "555-1234", "alice@email.com");
        Guest bob = new Guest("Bob Builder", "555-5678", "bob@email.com");
        guests.add(alice);
        guests.add(bob);

        generateHotelReport();

        checkAvailability();

        System.out.println("\n--- Making Reservations ---");
        Booking booking1 = makeReservation(alice, "Single", 5, "2025-09-06", "2025-09-11");
        Booking booking2 = makeReservation(bob, "Suite", 3, "2025-09-08", "2025-09-11");
        makeReservation(alice, "Suite", 2, "2025-10-01", "2025-10-03");

        if (booking1 != null) booking1.display();

        generateHotelReport();

        cancelReservation(booking1.bookingId);

        System.out.println("\n--- Final State ---");
        checkAvailability();
        generateHotelReport();
    }
}

