public class Q4_3LP {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName = "Default Rentals";
    private static int rentalDays = 0;
    private static int vehicleIdCounter = 0;

    public Q4_3LP(String brand, String model, double rentPerDay) {
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.vehicleId = generateVehicleId();
        totalVehicles++;
    }

    private static String generateVehicleId() {
        vehicleIdCounter++;
        return "VID" + String.format("%03d", vehicleIdCounter);
    }

    public boolean rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println(vehicleId + " is currently not available.");
            return false;
        }
        if (days <= 0) {
            System.out.println("Rental days must be positive.");
            return false;
        }
        double rentAmount = calculateRent(days);
        System.out.printf("%s rented for %d days. Amount: $%.2f\n", vehicleId, days, rentAmount);
        isAvailable = false;
        rentalDays += days;
        totalRevenue += rentAmount;
        return true;
    }

    public void returnVehicle() {
        if (isAvailable) {
            System.out.println(vehicleId + " was not rented.");
        } else {
            isAvailable = true;
            System.out.println(vehicleId + " is now returned and available.");
        }
    }

    public double calculateRent(int days) {
        return days * rentPerDay;
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.printf("Rent per Day: $%.2f\n", rentPerDay);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("---------------------------");
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (rentalDays == 0) return 0;
        return totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company: " + companyName);
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.printf("Total Revenue: $%.2f\n", totalRevenue);
        System.out.printf("Average Rent Per Day: $%.2f\n", getAverageRentPerDay());
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        setCompanyName("Speedy Rentals");
        Q4_3LP[] fleet = new Q4_3LP[3];
        fleet[0] = new Q4_3LP("Toyota", "Camry", 40);
        fleet[1] = new Q4_3LP("Honda", "Civic", 35);
        fleet[2] = new Q4_3LP("Ford", "Mustang", 70);

        System.out.println("--- Initial Fleet Status ---");
        for (Q4_3LP v : fleet) v.displayVehicleInfo();

        System.out.println("\n--- Rental Transactions ---");
        fleet[0].rentVehicle(3); // 3 days * $40 = $120
        fleet[1].rentVehicle(5); // 5 days * $35 = $175
        fleet[2].rentVehicle(2); // 2 days * $70 = $140

        System.out.println("\n--- Vehicle Return and Re-rental ---");
        fleet[1].returnVehicle();
        fleet[1].rentVehicle(1); // Rent again for 1 day * $35 = $35

        System.out.println("\n--- Company Statistics ---");
        displayCompanyStats();
    }
}

