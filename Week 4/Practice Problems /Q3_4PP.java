public class Q3_4PP {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;
    // No-argument constructor using this() chaining
    public Q3_4PP() {
// Calls three-parameter constructor with defaults
        this("StandardMix-8", 8, false);
    }
    // Two-parameter constructor using this() chaining
    public Q3_4PP(String mixerModel, int numberOfChannels) {
// Calls three-parameter constructor with bluetooth disabled
        this(mixerModel, numberOfChannels, false);
    }
    // Three-parameter constructor using this() chaining
    public Q3_4PP(String mixerModel, int numberOfChannels, boolean
            hasBluetoothConnectivity) {
// Calls main constructor with default max volume
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity,
                120.0);
    }
    // Main constructor - all parameters
    public Q3_4PP(String mixerModel, int numberOfChannels, boolean
            hasBluetoothConnectivity, double maxVolumeDecibels) {
// The only constructor that performs the actual initialization
        System.out.println("--> Main 4-parameter constructor executed for '" + mixerModel + "'");
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;
    }
    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("'" + deviceName + "' connected to " +
                    mixerModel);
        } else {
            System.out.println("All channels on " + mixerModel + " are occupied!");
        }
    }
    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ?
                "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" +
                numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " +
                    connectedDevices[i]);
        }
    }
    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===");
// Create mixer using no-argument constructor
        System.out.println("\n1. Creating default mixer...");
        Q3_4PP defaultMixer = new Q3_4PP();
// Create mixer using two-parameter constructor
        System.out.println("\n2. Creating basic mixer...");
        Q3_4PP basicMixer = new Q3_4PP("BasicMix-4", 4);
// Create mixer using three-parameter constructor
        System.out.println("\n3. Creating bluetooth mixer...");
        Q3_4PP bluetoothMixer = new Q3_4PP("BlueMix-12", 12, true);
// Create mixer using full constructor
        System.out.println("\n4. Creating pro mixer...");
        Q3_4PP proMixer = new Q3_4PP("ProAudio-24", 24, true, 135.5);
        System.out.println("\n--- Connecting Devices ---");
        defaultMixer.connectDevice("Microphone 1");
        basicMixer.connectDevice("Guitar");
        basicMixer.connectDevice("Bass");
        proMixer.connectDevice("Main Keyboard");
        proMixer.connectDevice("Drum Machine");
        proMixer.connectDevice("Vocal Processor");
// Display status of all mixers
        defaultMixer.displayMixerStatus();
        basicMixer.displayMixerStatus();
        bluetoothMixer.displayMixerStatus();
        proMixer.displayMixerStatus();
        System.out.println("\n// Note: The 'Main constructor executed' message shows that all other constructors");
                System.out.println("// eventually 'chain' their calls to the single, primary constructor for initialization.");
    }
}