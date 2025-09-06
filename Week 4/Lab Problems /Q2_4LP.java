import java.util.ArrayList;
import java.util.List;
// Abstract base class for all magical structures
abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;
    // Constructor chaining
    public MagicalStructure(String name, String location) {
        this(name, location, 10, true); // Default magic power andactive status
    }
    public MagicalStructure(String name, String location, int
            magicPower, boolean isActive) {
        this.structureName = name;
        this.location = location;
        this.magicPower = magicPower;
        this.isActive = isActive;
    }
    // Abstract method must be implemented by subclasses
    public abstract String castMagicSpell();
    public void displayStatus() {
        System.out.printf("'%s' at %s | Power: %d | Status: %s\n",
                structureName, location, magicPower, isActive ? "Active" :
                        "Inactive");
    }
}
// Derived classes
class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private List<String> knownSpells = new ArrayList<>();
    public WizardTower(String name, String location, int spellCapacity)
    {
        super(name, location, 50, true); // Wizard towers are powerful
        this.spellCapacity = spellCapacity;
        this.knownSpells.add("Fireball");
    }
    @Override
    public String castMagicSpell() {
        return structureName + " casts a powerful Fireball!";
    }
}
class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;
    public EnchantedCastle(String name, String location, int defense,
                           boolean drawbridge) {
        super(name, location, 20, true);
        this.defenseRating = defense;
        this.hasDrawbridge = drawbridge;
    }
    public void tripleDefense() { this.defenseRating *= 3; }
    @Override
    public String castMagicSpell() {
        return structureName + " projects a defensive shield!";
    }
}
class MysticLibrary extends MagicalStructure {
    private int bookCount;
    public MysticLibrary(String name, String location, int books) {
        super(name, location, 15, true);
        this.bookCount = books;
    }
    @Override
    public String castMagicSpell() {
        return structureName + " reveals an ancient secret!";
    }
}
class DragonLair extends MagicalStructure {
    private String dragonType;
    public DragonLair(String name, String location, String dragon) {
        super(name, location, 100, true); // Lairs are very powerful
        this.dragonType = dragon;
    }
    @Override
    public String castMagicSpell() {
        return "The " + dragonType + " dragon from " + structureName + " breathes fire!";
    }
}
public class Q2_4LP {
    // KingdomManager static methods
    public static void checkStructureInteractions(MagicalStructure[]
                                                          kingdom) {
        System.out.println("\n--- Checking for Magical Synergies ---");
        WizardTower tower = null;
        MysticLibrary library = null;
        EnchantedCastle castle = null;
        DragonLair lair = null;
// Use instanceof to find specific structures
        for (MagicalStructure s : kingdom) {
            if (s instanceof WizardTower) tower = (WizardTower) s;
            if (s instanceof MysticLibrary) library = (MysticLibrary) s;
            if (s instanceof EnchantedCastle) castle = (EnchantedCastle)
                    s;
            if (s instanceof DragonLair) lair = (DragonLair) s;
        }
        if (tower != null && library != null) {
            System.out.println("SYNERGY! Wizard Tower + Mystic Library = Knowledge Boost!");
        }
        if (castle != null && lair != null) {
            System.out.println("SYNERGY! Enchanted Castle + Dragon Lair = Dragon Guard! Defense tripled!");
            castle.tripleDefense();
        }
    }
    public static String performMagicBattle(MagicalStructure attacker,
                                            MagicalStructure defender) {
        System.out.println("\n--- MAGIC BATTLE ---");
        System.out.println(attacker.castMagicSpell());
        System.out.println(defender.castMagicSpell());
        if (attacker.magicPower > defender.magicPower) {
            return attacker.structureName + " wins!";
        } else if (defender.magicPower > attacker.magicPower) {
            return defender.structureName + " wins!";
        } else {
            return "It's a magical stalemate!";
        }
    }
    public static int calculateKingdomMagicPower(MagicalStructure[]
                                                         structures) {
        int totalPower = 0;
        for (MagicalStructure s : structures) {
            if (s.isActive) {
                totalPower += s.magicPower;
            }
        }
        return totalPower;
    }
    public static void main(String[] args) {
        System.out.println("=== MEDIEVAL KINGDOM BUILDER ===");
        MagicalStructure[] myKingdom = {
                new WizardTower("Tower of Eldoria", "Northern Mountains",
                        10),
                new EnchantedCastle("Castle Greystone", "Royal Capital",
                        500, true),
                new MysticLibrary("Archives of Ages", "Royal Capital",
                        5000),
                new DragonLair("Crimson Peak", "Volcanic Plains", "Red")
        };
        System.out.println("\n--- Kingdom Structures ---");
        for(MagicalStructure s : myKingdom) {
            s.displayStatus();
        }
        checkStructureInteractions(myKingdom);
        String battleResult = performMagicBattle(myKingdom[0],
                myKingdom[3]); // Tower vs Lair
        System.out.println("Battle Result: " + battleResult);
        System.out.println("\n--- Final Kingdom Status ---");
        for(MagicalStructure s : myKingdom) {
            s.displayStatus();
        }
        int totalPower = calculateKingdomMagicPower(myKingdom);
        System.out.println("\nTotal Kingdom Magic Power: " +
                totalPower);
    }
}