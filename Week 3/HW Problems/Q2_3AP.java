/**
 * Q2_3AP.java
 * Assignment 2: Online Shopping Cart System
 * This program demonstrates object relationships where a ShoppingCart object
 * manages multiple Product objects.
 */
import java.util.Arrays;

class Product {
    // --- Static Members ---
    private static int totalProducts = 0;
    private static final String[] categories = {"Electronics", "Books", "Clothing", "Groceries"};

    // --- Instance Members ---
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    // --- Constructor ---
    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    // --- Getters required for other classes to access private data ---
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }

    public void reduceStock(int quantity) {
        if(quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }

    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }

    public void display() {
        System.out.printf("ID: %s, Name: %s, Price: $%.2f, Stock: %d\n", productId, productName, price, stockQuantity);
    }

    // --- Static Methods ---
    public static int getTotalProducts() { return totalProducts; }
    public static String[] getCategories() { return categories; }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null; // Not found
    }

    public static void getProductsByCategory(Product[] products, String category) {
        System.out.println("\n--- Products in Category: " + category + " ---");
        boolean found = false;
        for (Product p : products) {
            if (p != null && p.getCategory().equalsIgnoreCase(category)) {
                p.display();
                found = true;
            }
        }
        if (!found) System.out.println("No products found in this category.");
        System.out.println("----------------------------------------");
    }
}

class ShoppingCart {
    // --- Instance Members ---
    private String cartId;
    private String customerName;
    private Product[] products; // Array of Product objects
    private int[] quantities;   // Array of quantities for each product
    private int itemCount;      // Number of unique items in the cart
    private double cartTotal;

    private static int cartIdCounter = 0;

    // --- Constructor ---
    public ShoppingCart(String customerName) {
        this.customerName = customerName;
        this.cartId = "CART" + String.format("%03d", ++cartIdCounter);
        this.products = new Product[10]; // Max 10 unique products
        this.quantities = new int[10];
        this.itemCount = 0;
        this.cartTotal = 0.0;
    }

    // --- Instance Methods ---
    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Invalid product or quantity.");
            return;
        }
        if (product.getStockQuantity() < quantity) {
            System.out.println("Error: Not enough stock for " + product.getProductName());
            return;
        }

        for(int i = 0; i < itemCount; i++) {
            if(products[i].getProductId().equals(product.getProductId())) {
                System.out.println("Product already in cart. Please use an 'update quantity' function if needed.");
                return;
            }
        }

        if (itemCount < products.length) {
            products[itemCount] = product;
            quantities[itemCount] = quantity;
            itemCount++;
            System.out.println(quantity + "x " + product.getProductName() + " added to cart.");
            calculateTotal();
        } else {
            System.out.println("Cart is full.");
        }
    }

    public void removeProduct(String productId) {
        int foundIndex = -1;
        for (int i = 0; i < itemCount; i++) {
            if (products[i].getProductId().equals(productId)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println(products[foundIndex].getProductName() + " removed from cart.");
            for (int i = foundIndex; i < itemCount - 1; i++) {
                products[i] = products[i + 1];
                quantities[i] = quantities[i + 1];
            }
            itemCount--;
            products[itemCount] = null;
            quantities[itemCount] = 0;
            calculateTotal();
        } else {
            System.out.println("Product with ID " + productId + " not found in cart.");
        }
    }

    public void calculateTotal() {
        cartTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            cartTotal += products[i].getPrice() * quantities[i];
        }
    }

    public void displayCart() {
        System.out.println("\n--- Shopping Cart for " + customerName + " (ID: " + cartId + ") ---");
        if (itemCount == 0) {
            System.out.println("Cart is empty.");
        } else {
            for (int i = 0; i < itemCount; i++) {
                System.out.printf("- %s (ID: %s) | Quantity: %d | Price: $%.2f | Subtotal: $%.2f\n",
                        products[i].getProductName(), products[i].getProductId(), quantities[i], products[i].getPrice(), products[i].getPrice() * quantities[i]);
            }
        }
        System.out.printf(">>> Cart Total: $%.2f\n", cartTotal);
        System.out.println("-----------------------------------------------------");
    }

    public void checkout() {
        if (itemCount == 0) {
            System.out.println("Cannot checkout with an empty cart.");
            return;
        }
        System.out.println("\n--- Checking out ---");
        displayCart();
        System.out.println("Processing payment...");
        for (int i = 0; i < itemCount; i++) {
            products[i].reduceStock(quantities[i]);
        }
        System.out.println("Checkout complete. Thank you for your purchase, " + customerName + "!");
        Arrays.fill(products, null);
        Arrays.fill(quantities, 0);
        itemCount = 0;
        cartTotal = 0.0;
    }
}


public class Q2_3AP {
    public static void main(String[] args) {
        Product[] productCatalog = new Product[10];
        productCatalog[0] = new Product("P001", "Laptop", 999.99, "Electronics", 50);
        productCatalog[1] = new Product("P002", "Smartphone", 699.50, "Electronics", 150);
        productCatalog[2] = new Product("P003", "Intro to Java", 49.99, "Books", 200);
        productCatalog[3] = new Product("P004", "The Great Gatsby", 15.00, "Books", 300);
        productCatalog[4] = new Product("P005", "T-Shirt", 25.00, "Clothing", 500);
        productCatalog[5] = new Product("P006", "Jeans", 75.00, "Clothing", 250);
        productCatalog[6] = new Product("P007", "Milk", 3.50, "Groceries", 1000);
        productCatalog[7] = new Product("P008", "Bread", 2.25, "Groceries", 1500);
        productCatalog[8] = new Product("P009", "Headphones", 150.00, "Electronics", 80);
        productCatalog[9] = new Product("P010", "Data Structures", 85.00, "Books", 120);

        System.out.println("Welcome to the Online Store! Total products available: " + Product.getTotalProducts());
        Product.getProductsByCategory(productCatalog, "Electronics");

        ShoppingCart cart = new ShoppingCart("John Doe");
        cart.displayCart();

        System.out.println("\n--- Adding products to cart ---");
        Product laptop = Product.findProductById(productCatalog, "P001");
        Product javaBook = Product.findProductById(productCatalog, "P003");
        Product headphones = Product.findProductById(productCatalog, "P009");

        cart.addProduct(laptop, 1);
        cart.addProduct(javaBook, 2);
        cart.addProduct(headphones, 500);

        cart.displayCart();

        System.out.println("\n--- Removing a product from cart ---");
        cart.removeProduct("P003");
        cart.displayCart();

        cart.checkout();

        System.out.println("\n--- Stock levels after checkout ---");
        laptop.display();
        javaBook.display();
    }
}

