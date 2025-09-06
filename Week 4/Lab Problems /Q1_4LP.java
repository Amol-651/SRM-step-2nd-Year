import java.util.Random;
import java.util.ArrayList;
import java.util.List;
class VirtualPet {
    // --- Instance and Static Fields ---
    final String petId; // A unique, unchangeable ID for the pet
    String petName;
    String species;
    int age;
    int happiness; // Scale of 0-100
    int health; // Scale of 0-100
    String currentStage;
    // Static fields are shared across all instances of the class
    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child",
            "Teen", "Adult", "Elder", "Ghost"};
    static int totalPetsCreated = 0;
    private static int petIdCounter = 0;
    private static final Random random = new Random();
    // --- Constructors with 'this()' Chaining ---
// Default constructor: Creates a mysterious egg
    public VirtualPet() {
// Chains to the main constructor with default "Egg" values
        this("Mysterious Egg", "Unknown", 0, 50, 100,
                EVOLUTION_STAGES[0]);
    }
    // Constructor with name only: Pet starts as a baby
    public VirtualPet(String petName) {
// Chains to the main constructor, starting at the "Baby" stage
        this(petName, "Critter", 1, 70, 80, EVOLUTION_STAGES[1]);
    }
    // Constructor with name and species: Pet starts as a child
    public VirtualPet(String petName, String species) {
// Chains to the main constructor, starting at the "Child" stage
        this(petName, species, 5, 80, 90, EVOLUTION_STAGES[2]);
    }
    // Main constructor: The one that does the actual initialization
    public VirtualPet(String petName, String species, int age, int
            happiness, int health, String stage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.currentStage = stage;
        totalPetsCreated++;
        System.out.println("A new pet, " + this.petName + " (" +
                this.petId + "), has been created as a " + this.currentStage + "!");
    }
    // --- Static Method ---
    public static String generatePetId() {
        return "VP" + String.format("%04d", ++petIdCounter);
    }
    // --- Instance Methods ---
    public void evolvePet() {
        if (currentStage.equals("Ghost")) return; // Ghosts can't evolve
        String originalStage = this.currentStage;
        if (age >= 30 && health > 50) this.currentStage =
                EVOLUTION_STAGES[5]; // Elder
        else if (age >= 15 && health > 60) this.currentStage =
                EVOLUTION_STAGES[4]; // Adult
        else if (age >= 8 && health > 70) this.currentStage =
                EVOLUTION_STAGES[3]; // Teen
        else if (age >= 3 && health > 80) this.currentStage =
                EVOLUTION_STAGES[2]; // Child
        else if (age >= 1 && health > 90) this.currentStage =
                EVOLUTION_STAGES[1]; // Baby
        if (!originalStage.equals(this.currentStage)) {
            System.out.println("-> EVOLUTION! " + petName + " has evolved from a " + originalStage + " to a " + this.currentStage + "!");
        }
    }
    public void feedPet() {
        if (currentStage.equals("Ghost") || currentStage.equals("Egg"))
            return;
        this.health = Math.min(100, this.health + 10);
        System.out.println(petName + " was fed. Health is now " +
                this.health + ".");
    }
    public void playWithPet() {
        if (currentStage.equals("Ghost") || currentStage.equals("Egg"))
            return;
        this.happiness = Math.min(100, this.happiness + 15);
        System.out.println(petName + " played. Happiness is now " +
                this.happiness + ".");
    }
    public void simulateDay() {
        if (currentStage.equals("Ghost")) {
            System.out.println(petName + " haunts the daycare... WooOOoo!");
            return;
        }
        if(currentStage.equals("Egg") && random.nextInt(3) == 0) {
            System.out.println("The egg hatched!");
            this.currentStage = EVOLUTION_STAGES[1];
            this.petName = "Newly Hatched";
            this.age = 1;
        }
        this.age++;
        this.happiness -= random.nextInt(10);
        this.health -= random.nextInt(10);
        System.out.println("A day passes for " + petName + ". Age: " +
                age + ", Health: " + health + ", Happiness: " + happiness);
        if (this.health <= 0) {
            this.currentStage = EVOLUTION_STAGES[6]; // Ghost
            this.health = 0;
            System.out.println("!!! Oh no! " + petName + " has become a ghost!");
        } else {
            evolvePet();
        }
    }
    public String getPetStatus() {
        return petName + " (" + species + ") - Stage: " + currentStage +
                ", Age: " + age + ", HP: " + health + ", Happy: " + happiness;
    }
}
public class Q1_4LP {
    public static void main(String[] args) {
        System.out.println("=== VIRTUAL PET DAYCARE SIMULATOR ===");
        List<VirtualPet> daycare = new ArrayList<>();
        daycare.add(new VirtualPet()); // An egg
        daycare.add(new VirtualPet("Sparky")); // A baby
        daycare.add(new VirtualPet("Fido", "Doggo")); // A child
        System.out.println("\n--- SIMULATION START ---");
        System.out.println("Total pets created: " +
                VirtualPet.totalPetsCreated);
        for (int day = 1; day <= 10; day++) {
            System.out.println("\n--- DAY " + day + " ---");
            for (VirtualPet pet : daycare) {
                pet.simulateDay();
                if(day % 2 == 0) pet.feedPet();
                if(day % 3 == 0) pet.playWithPet();
            }
        }
        System.out.println("\n--- FINAL PET STATUS ---");
        for (VirtualPet pet : daycare) {
            System.out.println(pet.getPetStatus());
        }
    }
}