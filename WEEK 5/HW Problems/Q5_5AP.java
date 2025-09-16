import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Q5_5AP - Vehicle Rental System (single-file)
 */
public class Q5_5AP {
    public static void main(String[] args) {
        System.out.println("=== Q5_5AP: Vehicle Rental Demo ===");
        Vehicle car = new Vehicle("V-100", "Sedan", 1200); // per-day cents/units
        Vehicle bike = new Vehicle("V-200", "Motorbike", 400);

        RentalService service = new RentalService();
        service.addVehicle(car);
        service.addVehicle(bike);

        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(3);
        String bookingId = service.rentVehicle("V-100", "CUST-1", from, to);
        System.out.println("Booking id: " + bookingId);
        System.out.println("Charge: " + service.getBookingCharge(bookingId));
    }

    static final class Vehicle {
        private final String id;
        private final String type;
        private final double ratePerDay;
        private boolean available;

        public Vehicle(String id, String type, double ratePerDay) {
            this.id = id;
            this.type = type;
            this.ratePerDay = ratePerDay;
            this.available = true;
        }

        public String getId() { return id; }
        public String getType() { return type; }
        public double getRatePerDay() { return ratePerDay; }
        public boolean isAvailable() { return available; }

        private void setAvailable(boolean val) { this.available = val; }
    }

    static final class Booking {
        private final String bookingId;
        private final String vehicleId;
        private final String customerId;
        private final LocalDate from;
        private final LocalDate to;

        Booking(String bookingId, String vehicleId, String customerId, LocalDate from, LocalDate to) {
            this.bookingId = bookingId;
            this.vehicleId = vehicleId;
            this.customerId = customerId;
            this.from = from;
            this.to = to;
        }

        public String getBookingId() { return bookingId; }
        public String getVehicleId() { return vehicleId; }
        public String getCustomerId() { return customerId; }
        public LocalDate getFrom() { return from; }
        public LocalDate getTo() { return to; }
    }

    static class RentalService {
        private final Map<String, Vehicle> fleet = new HashMap<>();
        private final Map<String, Booking> bookings = new HashMap<>();

        public void addVehicle(Vehicle v) { fleet.put(v.getId(), v); }

        public String rentVehicle(String vehicleId, String customerId, LocalDate from, LocalDate to) {
            Vehicle v = fleet.get(vehicleId);
            if (v == null) throw new IllegalArgumentException("No such vehicle");
            if (!v.isAvailable()) throw new IllegalStateException("Vehicle not available");
            if (to.isBefore(from)) throw new IllegalArgumentException("Invalid date range");

            String bookingId = UUID.randomUUID().toString();
            Booking b = new Booking(bookingId, vehicleId, customerId, from, to);
            bookings.put(bookingId, b);
            v.setAvailable(false);
            return bookingId;
        }

        public double getBookingCharge(String bookingId) {
            Booking b = bookings.get(bookingId);
            if (b == null) throw new IllegalArgumentException("No such booking");
            Vehicle v = fleet.get(b.getVehicleId());
            long days = ChronoUnit.DAYS.between(b.getFrom(), b.getTo()) + 1;
            double base = v.getRatePerDay() * days;
            // simple policy: long rental discount
            if (days >= 7) base *= 0.9;
            return Math.round(base * 100.0) / 100.0;
        }

        public boolean returnVehicle(String bookingId) {
            Booking b = bookings.remove(bookingId);
            if (b == null) return false;
            Vehicle v = fleet.get(b.getVehicleId());
            if (v != null) v.setAvailable(true);
            return true;
        }
    }
}
