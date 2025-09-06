public class Q4_4AP {
    String customerName;
    String foodItem;
    int quantity;
    double totalPrice;
    private static final double FIXED_RATE = 150.0; // Fixed price peritem
    // 1. Default constructor
    public Q4_4AP() {
        this("Unknown Customer", "No Item", 0);
    }
    // 2. Constructor with food item
    public Q4_4AP(String foodItem) {
        this("Guest", foodItem, 1);
    }
    // 3. Constructor with food item and quantity
    public Q4_4AP(String customerName, String foodItem, int quantity) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
// Price calculation happens directly in the constructor
        this.totalPrice = quantity * FIXED_RATE;
    }
    public void printBill() {
        System.out.println("\n--- ORDER BILL ---");
        System.out.println("Customer: " + customerName);
        System.out.println("Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Total Price: $%.2f\n", totalPrice);
        System.out.println("------------------");
    }
    public static void main(String[] args) {
// Create multiple orders
        Q4_4AP order1 = new Q4_4AP("Pizza");
        Q4_4AP order2 = new Q4_4AP("Alice", "Burger", 2);
        Q4_4AP order3 = new Q4_4AP("Bob", "Pasta", 3);
        System.out.println("Printing all bills...");
        order1.printBill();
        order2.printBill();
        order3.printBill();
    }
}