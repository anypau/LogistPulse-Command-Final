package logistpulse.observer;

import java.util.ArrayList;
import java.util.List;

public final class AuditObserver implements InventoryObserver {
    private final List<String> events = new ArrayList<>();
    public void onInventoryChanged(String product, int previous, int current) {
        String event = product + ": " + previous + " -> " + current;
        events.add(event);
        System.out.println("AUDIT: " + event);
    }
    public List<String> getEvents() { return List.copyOf(events); }
}
