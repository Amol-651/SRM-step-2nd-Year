import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class Q2_5LP {
    public static void main(String[] args) {
        System.out.println("=== Practice Problem 2: Medieval Kingdom Management ===");
        KingdomConfig myKingdomConfig = KingdomConfig.createDefaultKingdom();
        KingdomManager kingdomManager = new KingdomManager(myKingdomConfig);
        WizardTower tower = new WizardTower("The Arcane Spire");
        EnchantedCastle castle = new EnchantedCastle("Cloudhaven Castle", "Royal");
        MysticLibrary library = new MysticLibrary("The Great Library");
        DragonLair lair = new DragonLair("Wyvern's Peak", "Red Dragon");
        kingdomManager.addStructure(tower);
        kingdomManager.addStructure(castle);
        kingdomManager.addStructure(library);
        kingdomManager.addStructure(lair);
        System.out.println("\nChecking magical interactions:");
        KingdomManager.canStructuresInteract(tower, library);
        KingdomManager.canStructuresInteract(castle, lair);
        System.out.println("\nSimulating a battle:");
        System.out.println(KingdomManager.performMagicBattle(tower, castle));
        System.out.println("\nKingdom Power Calculation:");
        System.out.println("Total kingdom power: " +
                KingdomManager.calculateKingdomPower(kingdomManager.structures));
        System.out.println("\nCategorizing structures:");
        System.out.println(tower.getStructureName() + " is a " +
                kingdomManager.determineStructureCategory(tower));
        System.out.println(castle.getStructureName() + " is an " +
                kingdomManager.determineStructureCategory(castle));
    }

    // Kingdom configuration
    static final class KingdomConfig {
        private final String kingdomName;
        private final int foundingYear;
        private final String[] allowedStructureTypes;
        private final Map<String, Integer> resourceLimits;

        public KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes, Map<String, Integer> resourceLimits) {
            if (kingdomName == null || allowedStructureTypes == null || resourceLimits == null) {
                throw new IllegalArgumentException("Configuration parameters cannot be null.");
            }
            this.kingdomName = kingdomName;
            this.foundingYear = foundingYear;
            this.allowedStructureTypes = allowedStructureTypes.clone();
            this.resourceLimits = new HashMap<>(resourceLimits);
        }

        public static KingdomConfig createDefaultKingdom() {
            String[] defaultStructures = {"WizardTower", "EnchantedCastle"};
            Map<String, Integer> defaultLimits = new HashMap<>();
            defaultLimits.put("wood", 1000);
            return new KingdomConfig("Default Kingdom", 1000, defaultStructures, defaultLimits);
        }

        public String getKingdomName() {
            return kingdomName;
        }

        public int getFoundingYear() {
            return foundingYear;
        }

        public String[] getAllowedStructureTypes() {
            return allowedStructureTypes.clone();
        }

        public Map<String, Integer> getResourceLimits() {
            return new HashMap<>(resourceLimits);
        }
    }

    // Base magical structure
    static class MagicalStructure {
        private final String structureId;
        private final long constructionTimestamp;
        private final String structureName;
        private final String location;
        private int magicPower;
        private boolean isActive;
        private String currentMaintainer;
        static final int MIN_MAGIC_POWER = 0;
        static final int MAX_MAGIC_POWER = 1000;
        public static final String MAGIC_SYSTEM_VERSION = "3.0";

        public MagicalStructure(String name, String location, int power, boolean active) {
            this.structureId = UUID.randomUUID().toString();
            this.constructionTimestamp = System.currentTimeMillis();
            this.structureName = name;
            this.location = location;
            this.setMagicPower(power);
            this.isActive = active;
        }

        public MagicalStructure(String name, String location) {
            this(name, location, 10, true);
        }

        public MagicalStructure(String name, String location, int power) {
            this(name, location, power, true);
        }

        public String getStructureId() {
            return structureId;
        }

        public String getStructureName() {
            return structureName;
        }

        public String getLocation() {
            return location;
        }

        public int getMagicPower() {
            return magicPower;
        }

        public void setMagicPower(int magicPower) {
            if (magicPower < MIN_MAGIC_POWER || magicPower > MAX_MAGIC_POWER) {
                throw new IllegalArgumentException("Magic power out of range.");
            }
            this.magicPower = magicPower;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public void castMagicSpell() {
            if (isActive) {
                System.out.println(structureName + " casts a basic spell.");
            } else {
                System.out.println(structureName + " is inactive.");
            }
        }
    }

    // WizardTower
    static class WizardTower extends MagicalStructure {
        private final int maxSpellCapacity;
        private List<String> knownSpells;
        private String currentWizard;

        public WizardTower(String name) {
            this(name, "Mountaintop", 50, true, 25);
            this.knownSpells = new ArrayList<>(Collections.singletonList("Basic Ward"));
        }

        public WizardTower(String name, int maxSpellCapacity) {
            this(name, "Mountaintop", 50, true, maxSpellCapacity);
            this.knownSpells = new ArrayList<>(Collections.singletonList("Basic Ward"));
        }

        public WizardTower(String name, String location, int power, boolean active, int maxSpellCapacity) {
            super(name, location, power, active);
            this.maxSpellCapacity = maxSpellCapacity;
            this.knownSpells = new ArrayList<>();
        }

        @Override
        public void castMagicSpell() {
            if (isActive()) {
                System.out.println(getStructureName() + " casts a powerful illusion spell.");
            } else {
                System.out.println(getStructureName() + " is inactive.");
            }
        }
    }

    // EnchantedCastle
    static class EnchantedCastle extends MagicalStructure {
        private final String castleType;
        private int defenseRating;
        private boolean hasDrawbridge;

        public EnchantedCastle(String name, String type) {
            this(name, "Royal Keep", 100, true, type, 500, false);
        }

        public EnchantedCastle(String name, String location, int power, boolean active, String type, int defenseRating, boolean hasDrawbridge) {
            super(name, location, power, active);
            this.castleType = type;
            this.defenseRating = defenseRating;
            this.hasDrawbridge = hasDrawbridge;
        }

        @Override
        public void castMagicSpell() {
            if (isActive()) {
                System.out.println(getStructureName() + " summons a magical barrier.");
            } else {
                System.out.println(getStructureName() + " is dormant.");
            }
        }
    }

    // MysticLibrary
    static class MysticLibrary extends MagicalStructure {
        private final Map<String, String> bookCollection;
        private int knowledgeLevel;

        public MysticLibrary(String name) {
            super(name, "Hidden Grove");
            this.bookCollection = new HashMap<>();
            this.knowledgeLevel = 10;
            this.bookCollection.put("The Tome of Ages", "Ancient Language");
        }

        @Override
        public void castMagicSpell() {
            if (isActive()) {
                System.out.println(getStructureName() + " grants ancient knowledge.");
            } else {
                System.out.println(getStructureName() + " is silent.");
            }
        }
    }

    // DragonLair
    static class DragonLair extends MagicalStructure {
        private final String dragonType;
        private final long treasureValue;
        private final int territorialRadius;

        public DragonLair(String name, String dragonType) {
            this(name, "Volcanic Peak", 200, true, dragonType, 1000000L, 5000);
        }

        public DragonLair(String name, String location, int power, boolean active, String dragonType, long treasureValue, int territorialRadius) {
            super(name, location, power, active);
            this.dragonType = dragonType;
            this.treasureValue = treasureValue;
            this.territorialRadius = territorialRadius;
        }

        @Override
        public void castMagicSpell() {
            if (isActive()) {
                System.out.println(getStructureName() + " exhales a breath of fire!");
            } else {
                System.out.println(getStructureName() + " is deserted.");
            }
        }
    }

    // Manager
    static class KingdomManager {
        private final List<Object> structures;
        private final KingdomConfig config;

        public KingdomManager(KingdomConfig config) {
            this.config = config;
            this.structures = new ArrayList<>();
        }

        public void addStructure(Object structure) {
            structures.add(structure);
        }

        public static boolean canStructuresInteract(Object s1, Object s2) {
            if ((s1 instanceof WizardTower && s2 instanceof MysticLibrary) || (s1 instanceof MysticLibrary && s2 instanceof WizardTower)) {
                System.out.println("Wizard Tower and Mystic Library create a Knowledge Boost!");
                return true;
            } else if ((s1 instanceof EnchantedCastle && s2 instanceof DragonLair) || (s1 instanceof DragonLair && s2 instanceof EnchantedCastle)) {
                System.out.println("Castle and Dragon Lair create a Dragon Guard!");
                return true;
            }
            return false;
        }

        public static String performMagicBattle(Object attacker, Object defender) {
            if (!(attacker instanceof MagicalStructure) || !(defender instanceof MagicalStructure)) {
                return "Invalid battle participants.";
            }
            MagicalStructure a = (MagicalStructure) attacker;
            MagicalStructure d = (MagicalStructure) defender;
            if (a.isActive() && d.isActive()) {
                if (a.getMagicPower() > d.getMagicPower()) {
                    return a.getStructureName() + " overpowers " + d.getStructureName() + "!";
                } else if (d.getMagicPower() > a.getMagicPower()) {
                    return d.getStructureName() + " defends against " + a.getStructureName() + "!";
                } else {
                    return "The battle between " + a.getStructureName() + " and " +
                            d.getStructureName() + " is a stalemate.";
                }
            }
            return "One or both structures are inactive.";
        }

        public static int calculateKingdomPower(List<Object> structures) {
            int totalPower = 0;
            for (Object s : structures) {
                if (s instanceof MagicalStructure) {
                    totalPower += ((MagicalStructure) s).getMagicPower();
                }
            }
            return totalPower;
        }

        public String determineStructureCategory(Object structure) {
            if (structure instanceof WizardTower) return "WizardTower";
            if (structure instanceof EnchantedCastle) return "EnchantedCastle";
            if (structure instanceof MysticLibrary) return "MysticLibrary";
            if (structure instanceof DragonLair) return "DragonLair";
            return "Unknown";
        }
    }
}
