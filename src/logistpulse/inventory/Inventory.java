package logistpulse.inventory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Integer> products = new LinkedHashMap<>();

    public void addStock(String product, int quantity) {
        validate(product, quantity);
        products.put(product, getStock(product) + quantity);
        System.out.println("Added " + quantity + " units of " + product);
    }

    public void removeStock(String product, int quantity) {
        validate(product, quantity);
        if (getStock(product) < quantity) {
            throw new IllegalArgumentException("Not enough stock for " + product);
        }
        products.put(product, getStock(product) - quantity);
        System.out.println("Removed " + quantity + " units of " + product);
    }

    private void validate(String product, int quantity) {
        if (product == null || product.isBlank() || quantity <= 0) {
            throw new IllegalArgumentException("Invalid product or quantity");
        }
    }

    public int getStock(String product) {
        return products.getOrDefault(product, 0);
    }

    public Map<String, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public void showInventory() {
        System.out.println("\n--- CURRENT INVENTORY ---");
        products.forEach((product, quantity) -> System.out.println(product + ": " + quantity));
    }
}
