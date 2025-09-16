import java.util.UUID;
import java.util.Random;

public class Q1_5LP {
    public static void main(String[] args) {
        System.out.println("=== Practice Problem 1: Virtual Pet Evolution System ===");
        PetSpecies dragonSpecies = new PetSpecies("Dragon", new String[]{"Egg", "Hatchling",
                "Juvenile", "Adult", "Ancient"}, 200, "Mountain");
        VirtualPet defaultPet = new VirtualPet();
        VirtualPet namedPet = new VirtualPet("Sparky");
        VirtualPet customPet = new VirtualPet("Ignis", dragonSpecies);
        System.out.println("Version: " + VirtualPet.PET_SYSTEM_VERSION);
        System.out.println("Default Pet: " + defaultPet.getPetName() + " (" +
                defaultPet.getSpecies().getSpeciesName() + ")");
        System.out.println("Named Pet: " + namedPet.getPetName() + " (" +
                namedPet.getSpecies().getSpeciesName() + ")");
        System.out.println("Custom Pet: " + customPet.getPetName() + " (" +
                customPet.getSpecies().getSpeciesName() + ")");
        System.out.println("\nInteracting with " + customPet.getPetName() + "...");
        customPet.feedPet("meat");
        customPet.playWithPet("flight");
        customPet.setAge(15);
        System.out.println(customPet.getInternalState());
        customPet.feedPet("special");
        customPet.playWithPet("fetch");
    }

    // Nested immutable species class
    static final class PetSpecies {
        private final String speciesName;
        private final String[] evolutionStages;
        private final int maxLifespan;
        private final String habitat;

        public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
            if (speciesName == null || speciesName.isEmpty() || evolutionStages == null ||
                    evolutionStages.length == 0) {
                throw new IllegalArgumentException("Invalid species data.");
            }
            this.speciesName = speciesName;
            this.evolutionStages = evolutionStages.clone();
            this.maxLifespan = maxLifespan;
            this.habitat = habitat;
        }

        public String getSpeciesName() {
            return speciesName;
        }

        public String[] getEvolutionStages() {
            return evolutionStages.clone();
        }

        public int getMaxLifespan() {
            return maxLifespan;
        }

        public String getHabitat() {
            return habitat;
        }
    }

    // VirtualPet as nested static class
    static class VirtualPet {
        private final String petId;
        private final PetSpecies species;
        private final long birthTimestamp;
        private String petName;
        private int age;
        private int happiness;
        private int health;
        protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Baby", "Child",
                "Teen", "Adult"};
        static final int MAX_HAPPINESS = 100;
        static final int MAX_HEALTH = 100;
        public static final String PET_SYSTEM_VERSION = "2.0";

        public VirtualPet() {
            this(generateRandomPetName(), new PetSpecies("Default",
                    DEFAULT_EVOLUTION_STAGES, 50, "Cottage"));
        }

        public VirtualPet(String petName) {
            this(petName, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 50,
                    "Cottage"));
        }

        public VirtualPet(String petName, PetSpecies species) {
            this(petName, species, 0, 75, 75);
        }

        public VirtualPet(String petName, PetSpecies species, int age, int happiness, int health) {
            if (species == null) {
                throw new IllegalArgumentException("Pet species cannot be null.");
            }
            this.petId = generatePetId();
            this.birthTimestamp = System.currentTimeMillis();
            this.species = species;
            this.setPetName(petName);
            this.setAge(age);
            this.setHappiness(happiness);
            this.setHealth(health);
        }

        public String getPetId() {
            return petId;
        }

        public PetSpecies getSpecies() {
            return species;
        }

        public long getBirthTimestamp() {
            return birthTimestamp;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            if (age < 0 || age > species.getMaxLifespan()) {
                throw new IllegalArgumentException("Age out of range.");
            }
            this.age = age;
        }

        public int getHappiness() {
            return happiness;
        }

        public void setHappiness(int happiness) {
            this.happiness = validateStat(happiness, MAX_HAPPINESS);
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = validateStat(health, MAX_HEALTH);
        }

        public void feedPet(String foodType) {
            System.out.println(petName + " is eating " + foodType + ".");
            modifyHealth(calculateFoodBonus(foodType));
            updateEvolutionStage();
        }

        public void playWithPet(String gameType) {
            System.out.println(petName + " is playing " + gameType + ".");
            modifyHappiness(calculateGameEffect(gameType));
            updateEvolutionStage();
        }

        protected int calculateFoodBonus(String foodType) {
            return foodType.equalsIgnoreCase("special") ? 20 : 10;
        }

        protected int calculateGameEffect(String gameType) {
            return gameType.equalsIgnoreCase("fetch") ? 15 : 10;
        }

        private void modifyHappiness(int amount) {
            this.setHappiness(this.happiness + amount);
        }

        private void modifyHealth(int amount) {
            this.setHealth(this.health + amount);
        }

        private int validateStat(int stat, int max) {
            return Math.max(0, Math.min(max, stat));
        }

        private String generatePetId() {
            return UUID.randomUUID().toString();
        }

        private void updateEvolutionStage() {
            int stageIndex = getEvolutionStageIndex();
            if (age > 10 && stageIndex < species.getEvolutionStages().length - 1) {
                System.out.println(petName + " evolved to " +
                        species.getEvolutionStages()[stageIndex + 1]);
            }
        }

        private int getEvolutionStageIndex() {
            String currentStage = species.getEvolutionStages()[0];
            if (age > 40) currentStage = species.getEvolutionStages()[4];
            else if (age > 30) currentStage = species.getEvolutionStages()[3];
            else if (age > 20) currentStage = species.getEvolutionStages()[2];
            else if (age > 10) currentStage = species.getEvolutionStages()[1];
            for (int i = 0; i < species.getEvolutionStages().length; i++) {
                if (species.getEvolutionStages()[i].equals(currentStage)) {
                    return i;
                }
            }
            return 0;
        }

        String getInternalState() {
            return "ID: " + petId + ", Name: " + petName + ", Species: " + species.getSpeciesName()
                    + ", Age: " + age + ", Happiness: " + happiness + ", Health: " + health;
        }

        @Override
        public String toString() {
            return "VirtualPet{" +
                    "petId='" + petId + '\'' +
                    ", petName='" + petName + '\'' +
                    ", species=" + species.getSpeciesName() +
                    ", age=" + age +
                    ", happiness=" + happiness +
                    ", health=" + health +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VirtualPet that = (VirtualPet) o;
            return petId.equals(that.petId);
        }

        @Override
        public int hashCode() {
            return petId.hashCode();
        }

        private static String generateRandomPetName() {
            String[] names = {"Buddy", "Milo", "Luna", "Zig", "Pip"};
            return names[new Random().nextInt(names.length)];
        }
    }
}
