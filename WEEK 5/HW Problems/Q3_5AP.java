import java.util.*;

/**
 * Q3_5AP - Inventory & Warehouse System (single-file)
 */
public class Q3_5AP {
    public static void main(String[] args) {
        System.out.println("=== Q3_5AP: Inventory & Warehouse Demo ===");
        Product p1 = new Product("SKU-100", "Bottle", "Kitchenware", 50, 0.2);
        Product p2 = new Product("SKU-200", "Mug", "Kitchenware", 120, 0.35);

        Warehouse wh = new Warehouse("Central");
        wh.receiveStock(p1, 100);
        wh.receiveStock(p2, 40);

        System.out.println("Stock level SKU-100: " + wh.getStockLevel("SKU-100"));
        boolean removed = wh.dispatchStock("SKU-100", 10);
        System.out.println("Dispatched 10 from SKU-100? " + removed);
        System.out.println("Current inventory summary: " + wh.inventorySummary());
    }

    // immutable product
    static final class Product {
        private final String sku;
        private final String name;
        private final String category;
        private final double price;
        private final double weight;

        public Product(String sku, String name, String category, double price, double weight) {
            if (sku == null || sku.isEmpty()) throw new IllegalArgumentException("Bad SKU");
            this.sku = sku;
            this.name = name;
            this.category = category;
            this.price = price;
            this.weight = weight;
        }

        public String getSku() { return sku; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public double getWeight() { return weight; }

        @Override
        public String toString() { return sku + ":" + name; }
    }

    // Warehouse manages stock levels internally
    static class Warehouse {
        private final String name;
        private final Map<String, Integer> stockLevels; // sku -> qty
        private final Map<String, Product> catalog;     // sku -> product

        public Warehouse(String name) {
            this.name = name;
            this.stockLevels = new HashMap<>();
            this.catalog = new HashMap<>();
        }

        public String getName() { return name; }

        public synchronized void receiveStock(Product product, int qty) {
            if (product == null || qty <= 0) throw new IllegalArgumentException("Bad receive");
            catalog.putIfAbsent(product.getSku(), product);
            stockLevels.put(product.getSku(), stockLevels.getOrDefault(product.getSku(), 0) + qty);
            System.out.println("Received " + qty + " x " + product.getSku());
        }

        public synchronized boolean dispatchStock(String sku, int qty) {
            Integer current = stockLevels.getOrDefault(sku, 0);
            if (qty <= 0 || current < qty) {
                System.out.println("Dispatch failed for " + sku);
                return false;
            }
            stockLevels.put(sku, current - qty);
            System.out.println("Dispatched " + qty + " x " + sku);
            return true;
        }

        public int getStockLevel(String sku) {
            return stockLevels.getOrDefault(sku, 0);
        }

        public List<String> inventorySummary() {
            List<String> report = new ArrayList<>();
            for (Map.Entry<String, Integer> e : stockLevels.entrySet()) {
                Product p = catalog.get(e.getKey());
                report.add((p != null ? p.getName() : e.getKey()) + " => " + e.getValue());
            }
            return Collections.unmodifiableList(report);
        }
    }
}
