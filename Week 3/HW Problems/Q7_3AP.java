/**
 * Q7_3AP.java
 * Assignment 7: Vehicle Fleet Management System
 * This program demonstrates inheritance by creating a base Vehicle class and
 * specific subclasses (Car, Bus, Truck), managed within a fleet system.
 *
 * FIXED: Helper classes (Driver, Car, etc.) are now static nested classes
 * inside Q7_3AP to prevent "duplicate class" errors in a project with
 * multiple assignment files.
 */
import java.util.ArrayList;
import java.util.List;

public class Q7_3AP {

    // --- Nested Driver Class ---
    static class Driver {
        String driverId;
        String driverName;
        String licenseType;

        private static int driverIdCounter = 0;

        public Driver(String name, String license) {
            this.driverId = "DRV" + String.format("%03d", ++driverIdCounter);
            this.driverName = name;
            this.licenseType = license;
        }
    }

    // --- Base (Parent) Vehicle Class (Nested) ---
    static class Vehicle {
        protected String vehicleId;
        protected String brand;
        protected String model;
        protected int year;
        protected double mileage;
        protected String currentStatus;
        protected Driver assignedDriver;

        private static int totalVehicles = 0;
        private static String companyName = "Global Transport Inc.";

        private static int vehicleIdCounter = 0;

        public Vehicle(String brand, String model, int year, double mileage) {
            this.vehicleId = "V" + String.format("%04d", ++vehicleIdCounter);
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.mileage = mileage;
            this.currentStatus = "Available";
            totalVehicles++;
        }

        public static int getTotalVehicles() { return totalVehicles; }
        public static String getCompanyName() { return companyName; }

        public void assignDriver(Driver driver) {
            this.assignedDriver = driver;
            this.currentStatus = "On Trip";
            System.out.printf("Driver %s assigned to Vehicle %s (%s %s).\n",
                    driver.driverName, this.vehicleId, this.brand, this.model);
        }

        public void scheduleMaintenance() {
            this.currentStatus = "Maintenance";
            System.out.println("Vehicle " + vehicleId + " scheduled for maintenance.");
        }

        public void updateMileage(double miles) {
            this.mileage += miles;
        }

        public void displayDetails() {
            System.out.println("\n--- Vehicle Details ---");
            System.out.println("ID: " + vehicleId);
            System.out.println("Make/Model: " + brand + " " + model + " (" + year + ")");
            System.out.println("Mileage: " + mileage);
            System.out.println("Status: " + currentStatus);
            if (assignedDriver != null) {
                System.out.println("Assigned Driver: " + assignedDriver.driverName);
            }
        }
    }

    // --- Nested Subclass for Car ---
    static class Car extends Vehicle {
        private int seatingCapacity;

        public Car(String brand, String model, int year, double mileage, int seats) {
            super(brand, model, year, mileage);
            this.seatingCapacity = seats;
        }

        @Override
        public void displayDetails() {
            super.displayDetails();
            System.out.println("Type: Car");
            System.out.println("Seating Capacity: " + seatingCapacity);
        }
    }

    // --- Nested Subclass for Truck ---
    static class Truck extends Vehicle {
        private double loadCapacity;

        public Truck(String brand, String model, int year, double mileage, double load) {
            super(brand, model, year, mileage);
            this.loadCapacity = load;
        }

        @Override
        public void displayDetails() {
            super.displayDetails();
            System.out.println("Type: Truck");
            System.out.println("Load Capacity: " + loadCapacity + " tons");
        }
    }

    // --- Nested Subclass for Bus ---
    static class Bus extends Vehicle {
        private int passengerCapacity;

        public Bus(String brand, String model, int year, double mileage, int passengers) {
            super(brand, model, year, mileage);
            this.passengerCapacity = passengers;
        }

        @Override
        public void displayDetails() {
            super.displayDetails();
            System.out.println("Type: Bus");
            System.out.println("Passenger Capacity: " + passengerCapacity);
        }
    }

    // --- Main Fleet Management Logic ---
    private static List<Vehicle> fleet = new ArrayList<>();

    public static void getFleetUtilization() {
        long onTrip = fleet.stream().filter(v -> v.currentStatus.equals("On Trip")).count();
        long inMaintenance = fleet.stream().filter(v -> v.currentStatus.equals("Maintenance")).count();
        long available = fleet.stream().filter(v -> v.currentStatus.equals("Available")).count();

        System.out.println("\n====== Fleet Utilization Report for " + Vehicle.getCompanyName() + " ======");
        System.out.println("Total Vehicles: " + Vehicle.getTotalVehicles());
        System.out.println("Vehicles On Trip: " + onTrip);
        System.out.println("Vehicles in Maintenance: " + inMaintenance);
        System.out.println("Vehicles Available: " + available);
        System.out.println("====================================================");
    }

    public static void main(String[] args) {
        fleet.add(new Car("Toyota", "Camry", 2022, 15000, 5));
        fleet.add(new Truck("Volvo", "VNL", 2020, 120000, 20.5));
        fleet.add(new Bus("Mercedes", "Tourismo", 2021, 50000, 50));

        Driver john = new Driver("John Smith", "Car");
        Driver jane = new Driver("Jane Doe", "Heavy Vehicle");

        Vehicle car = fleet.get(0);
        Vehicle truck = fleet.get(1);

        car.displayDetails();
        truck.displayDetails();

        System.out.println("\n--- Assigning Drivers ---");
        car.assignDriver(john);
        truck.assignDriver(jane);

        getFleetUtilization();

        System.out.println("\n--- Scheduling Maintenance ---");
        truck.scheduleMaintenance();

        car.displayDetails();
        truck.displayDetails();
        getFleetUtilization();
    }
}

