package logistpulse.observer;

public final class LowStockObserver implements InventoryObserver {
    private final int threshold;
    public LowStockObserver(int threshold) {
        if (threshold < 0) throw new IllegalArgumentException("Invalid threshold");
        this.threshold = threshold;
    }

    public void onInventoryChanged(String product, int previous, int current) {
        if (current <= threshold && previous > threshold) {
            System.out.println("LOW STOCK ALERT: " + product + " (" + current + " units)");
        }
    }
}
