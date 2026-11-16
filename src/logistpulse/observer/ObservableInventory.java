package logistpulse.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ObservableInventory {
    private final Map<String, Integer> quantities = new LinkedHashMap<>();
    private final List<InventoryObserver> observers = new ArrayList<>();

    public void subscribe(InventoryObserver observer) {
        if (observer == null) throw new IllegalArgumentException("Observer required");
        observers.add(observer);
    }
    public void unsubscribe(InventoryObserver observer) { observers.remove(observer); }
    public int stock(String product) { return quantities.getOrDefault(product, 0); }

    public void adjust(String product, int delta) {
        if (product == null || product.isBlank()) throw new IllegalArgumentException("Product required");
        int previous = stock(product);
        long next = (long) previous + delta;
        if (next < 0 || next > Integer.MAX_VALUE) throw new IllegalArgumentException("Invalid resulting stock");
        quantities.put(product, (int) next);
        for (InventoryObserver observer : List.copyOf(observers)) {
            observer.onInventoryChanged(product, previous, (int) next);
        }
    }
}
