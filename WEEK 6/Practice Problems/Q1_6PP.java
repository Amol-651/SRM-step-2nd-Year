// Q1_6PP.java
// Inheritance practice: Vehicle (base) + Car (derived)

public class Q1_6PP {
    public static void main(String[] args) {
        System.out.println("=== Testing Default Constructor ===");
        Car car1 = new Car();
        car1.displaySpecs();
        car1.start();
        car1.openTrunk();
        car1.playRadio();

        System.out.println("\n=== Testing Parameterized Constructor ===");
        Car car2 = new Car("Toyota", "Corolla", 2022, "Hybrid", 4, "Hybrid", "Automatic");
        car2.displaySpecs();
        car2.start();
        System.out.println(car2.getVehicleInfo());
    }

    // Base class Vehicle (added so Car compiles/run standalone)
    static class Vehicle {
        private String make;
        private String model;
        private int year;
        private String vehicleType; // e.g., "Sedan", "SUV", "Hybrid"

        public Vehicle() {
            this.make = "Generic";
            this.model = "Model";
            this.year = 2000;
            this.vehicleType = "Unknown";
            System.out.println("Vehicle default constructor called");
        }

        public Vehicle(String make, String model, int year, String vehicleType) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.vehicleType = vehicleType;
            System.out.println("Vehicle parameterized constructor called");
        }

        public void start() {
            System.out.println("Vehicle is starting...");
        }

        public void stop() {
            System.out.println("Vehicle has stopped.");
        }

        public void displaySpecs() {
            System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year +
                    ", Type: " + vehicleType);
        }

        public String getVehicleInfo() {
            return make + " " + model + " (" + year + ") - " + vehicleType;
        }

        // Getters (kept for completeness)
        public String getMake() { return make; }
        public String getModel() { return model; }
        public int getYear() { return year; }
        public String getVehicleType() { return vehicleType; }
    }

    // Derived class Car
    static class Car extends Vehicle {
        private int numberOfDoors;
        private String fuelType;
        private String transmissionType;

        public Car() {
            // call Vehicle default implicitly
            this.numberOfDoors = 4;
            this.fuelType = "Petrol";
            this.transmissionType = "Manual";
            System.out.println("Car default constructor called");
        }

        // matches original signature: (String var1, String var2, int var3, String var4, int var5, String var6, String var7)
        public Car(String var1, String var2, int var3, String var4, int var5, String var6, String var7) {
            super(var1, var2, var3, var4);
            this.numberOfDoors = var5;
            this.fuelType = var6;
            this.transmissionType = var7;
            System.out.println("Car parameterized constructor called");
        }

        @Override
        public void start() {
            super.start();
            System.out.println("Car-specific startup sequence running...");
        }

        @Override
        public void displaySpecs() {
            super.displaySpecs();
            System.out.println("Doors: " + this.numberOfDoors +
                    ", Fuel: " + this.fuelType +
                    ", Transmission: " + this.transmissionType);
        }

        public void openTrunk() {
            System.out.println("Trunk opened");
        }

        public void playRadio() {
            System.out.println("Radio playing music");
        }
    }
}
