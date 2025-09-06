// Base class
public class Q4_3PP {
    // Protected instance variables (accessible to subclasses)
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;
    // Constructor
    public Q4_3PP(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }
    // Common methods
    public void startVehicle() {
        System.out.println(make + " " + model + " is starting.");
    }
    public void stopVehicle() {
        System.out.println(make + " " + model + " is stopping.");

    }
    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled by " + amount + " liters. Fuel level: " +
                fuelLevel);
    }
    public void displayVehicleInfo() {
        System.out.println("Vehicle Info:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Fuel Level: " + fuelLevel);
    }
    // Polymorphic method (can be overridden)
    public void drive() {
        System.out.println(make + " " + model + " is driving.");
    }
    // Main method to demonstrate reusability and polymorphism
    public static void main(String[] args) {
// Creating different types of vehicles using inheritance
        Q4_3PP car = new Car("Honda", "Civic", 2019, 50, 4);
        Q4_3PP truck = new Truck("Volvo", "FH16", 2018, 150, 10000);
        Q4_3PP motorcycle = new Motorcycle("Yamaha", "R15", 2021, 15, false);
// Array of Vehicle objects to demonstrate polymorphism
        Q4_3PP[] vehicles = {car, truck, motorcycle};
// Loop through all vehicles
        for (Q4_3PP v : vehicles) {
            v.displayVehicleInfo();
            v.startVehicle();
            v.drive(); // Polymorphic call
            v.refuel(10);
            v.stopVehicle();
            System.out.println("---------------------------");
        }
        /*
         * Explanation:
         * - This shows reusability because all vehicle types inherit from Vehicle,

         * so we don't have to rewrite common attributes or methods.
         * - Extensibility is shown by adding specific features in subclasses (like payload in Truck).
         * - Benefits: Code is cleaner, easier to maintain, and easy to add new vehicle types.
         */
    }
}
// Subclass Car
class Car extends Q4_3PP {
    private int numberOfDoors;
    public Car(String make, String model, int year, double fuelLevel, int numberOfDoors) {
        super(make, model, year, fuelLevel);
        this.numberOfDoors = numberOfDoors;
    }
    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Number of Doors: " + numberOfDoors);
    }
    @Override
    public void drive() {
        System.out.println(make + " " + model + " (Car) is cruising on the road.");
    }
}
// Subclass Truck
class Truck extends Q4_3PP {
    private double payloadCapacity; // in kilograms
    public Truck(String make, String model, int year, double fuelLevel, double payloadCapacity) {
        super(make, model, year, fuelLevel);
        this.payloadCapacity = payloadCapacity;
    }
    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Payload Capacity: " + payloadCapacity + " kg");
    }
    @Override

    public void drive() {
        System.out.println(make + " " + model + " (Truck) is hauling heavy cargo.");
    }
}
// Subclass Motorcycle
class Motorcycle extends Q4_3PP {
    private boolean hasSidecar;
    public Motorcycle(String make, String model, int year, double fuelLevel, boolean hasSidecar)
    {
        super(make, model, year, fuelLevel);
        this.hasSidecar = hasSidecar;
    }
    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Has Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }
    @Override
    public void drive() {
        System.out.println(make + " " + model + " (Motorcycle) is speeding through traffic.");
    }
}