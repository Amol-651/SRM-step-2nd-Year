public class Q4_4PP {
    private String deviceName;
    private String location;
    private boolean isOnline;
    private double powerConsumption;
    private String[] connectedDevices;
    private int connectionCount;
    // Constructor with parameter names matching field names
    public Q4_4PP(String deviceName, String location, boolean isOnline,
                  double powerConsumption) {
// Use `this` to distinguish between parameters and fields
        this.deviceName = deviceName;
        this.location = location;
        this.isOnline = isOnline;
        this.powerConsumption = powerConsumption;
        this.connectedDevices = new String[5];
        this.connectionCount = 0;
    }
    // Method using `this` for parameter disambiguation
    public void updateLocation(String location) {
        this.location = location;
        System.out.println(this.deviceName + " moved to " +
                this.location);
    }
    public void updatePowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
        System.out.println("Power consumption updated to " +
                this.powerConsumption + "W for " + this.deviceName);
    }
    // Method returning `this` for chaining
    public Q4_4PP setOnline(boolean isOnline) {
        this.isOnline = isOnline;
        System.out.println(this.deviceName + " is now " + (isOnline ?
                "Online" : "Offline"));
        return this; // Return the current object
    }
    public Q4_4PP connectToDevice(String deviceName) {
        if (this.connectionCount < this.connectedDevices.length) {
            this.connectedDevices[this.connectionCount] = deviceName;
            this.connectionCount++;
            System.out.println(this.deviceName + " connected to " +
                    deviceName);
        } else {
            System.out.println("Cannot connect to " + deviceName + ". Maximum connections reached for " + this.deviceName);
        }
        return this; // Enable method chaining
    }
    public Q4_4PP rename(String deviceName) {
        String oldName = this.deviceName;
        this.deviceName = deviceName;
        System.out.println("Device renamed from '" + oldName + "' to '"
                + this.deviceName + "'");
        return this;
    }
    public void displayDeviceInfo() {
        System.out.println("\n=== " + this.deviceName + " INFO ===");
        System.out.println("Location: " + this.location);
        System.out.println("Status: " + (this.isOnline ? "Online" :
                "Offline"));
        System.out.println("Power: " + this.powerConsumption + "W");
        System.out.println("Connections: " + this.connectionCount);
        for (int i = 0; i < this.connectionCount; i++) {
            System.out.println(" -> " + this.connectedDevices[i]);
        }
    }
    // Method that calls other methods using `this`
    public void performInitialSetup() {
        System.out.println("\n--- Performing initial setup for " +
                this.deviceName + " ---");
// `this` is implicit here, but can be used for clarity
        this.setOnline(true);
        this.updatePowerConsumption(5.0); // example setup value
        System.out.println(this.deviceName + " initial setup completed.");
    }
    public static void main(String[] args) {
        System.out.println("=== SMART HOME DEVICE NETWORK ===");
// Create devices
        Q4_4PP smartHub = new Q4_4PP("Generic Hub", "Living Room",
                false, 10.0);
        Q4_4PP smartLight = new Q4_4PP("Ceiling Light", "Bedroom",
                false, 7.5);
        smartHub.displayDeviceInfo();
// Demonstrate method chaining using the returned `this`
        System.out.println("\n--- Configuring Smart Hub with Method Chaining ---");
                smartHub.setOnline(true)
                        .connectToDevice("Smart TV")
                        .connectToDevice("Alexa")
                        .rename("Living Room Hub");
        smartHub.displayDeviceInfo();
// Demonstrate a setup routine
        smartLight.performInitialSetup();
        smartLight.displayDeviceInfo();
    }
}