/**
 * Q2_4PP.java
 * Practice Problem 2: Gaming Controller Configuration System
 * Demonstrates default, fully parameterized, and convenience constructors,
 * including basic input validation.
 */
public class Q2_4PP {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    // Default constructor - creates standard gaming setup
    public Q2_4PP() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    // Parameterized constructor for custom configuration
    public Q2_4PP(String controllerBrand, String connectionType, boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;

        // Validate battery level (0-100)
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Invalid battery level (" + batteryLevel + "). Setting to 100.");
            this.batteryLevel = 100;
        }

        // Validate sensitivity (0.1-3.0)
        if (sensitivity >= 0.1 && sensitivity <= 3.0) {
            this.sensitivity = sensitivity;
        } else {
            System.out.println("Invalid sensitivity (" + sensitivity + "). Setting to 1.0.");
            this.sensitivity = 1.0;
        }
    }

    // Two-parameter convenience constructor
    public Q2_4PP(String brand, String connectionType) {
        // Calls the main constructor using this() with default values for other fields
        this(brand, connectionType, true, 100, 1.5);
    }

    // Methods to test functionality
    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
        System.out.println("Calibration complete.");
    }

    public void displayConfiguration() {
        System.out.println("\n--- Controller: " + controllerBrand + " ---");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful for " + controllerBrand + "!");
        } else {
            System.out.println("Vibration is disabled on the " + controllerBrand + " controller.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");

        // Create controller with default constructor
        Q2_4PP defaultController = new Q2_4PP();

        // Create controller with full parameterized constructor
        Q2_4PP proController = new Q2_4PP("ProGamer", "Bluetooth", true, 85, 2.5);

        // Create controller with convenience constructor
        Q2_4PP wirelessController = new Q2_4PP("EasyPlay", "Wireless Dongle");

        // Test all methods on each controller
        defaultController.displayConfiguration();
        defaultController.calibrateController();
        defaultController.testVibration();

        proController.displayConfiguration();
        proController.calibrateController();
        proController.testVibration();

        wirelessController.displayConfiguration();
        wirelessController.calibrateController();
        wirelessController.testVibration();
    }
}

