package logistpulse.observer;

public interface InventoryObserver {
    void onInventoryChanged(String product, int previous, int current);
}
