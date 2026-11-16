package logistpulse;

import logistpulse.state.InventoryLot;
import logistpulse.strategy.PickingStrategy;
import logistpulse.strategy.FifoStrategy;
import logistpulse.strategy.LifoStrategy;
import logistpulse.strategy.FefoStrategy;
import logistpulse.observer.ObservableInventory;
import logistpulse.observer.LowStockObserver;
import logistpulse.observer.AuditObserver;
import java.time.LocalDate;
import java.util.List;

public final class DesignPatternsDemo {
    public static void main(String[] args) {
        System.out.println("=== LOGISTPULSE: STATE, STRATEGY, OBSERVER ===");

        InventoryLot older = new InventoryLot("LOT-A", "Medicine", 12,
            LocalDate.of(2026, 1, 1), LocalDate.of(2027, 6, 1));
        InventoryLot newer = new InventoryLot("LOT-B", "Medicine", 15,
            LocalDate.of(2026, 5, 1), LocalDate.of(2027, 1, 1));
        List<InventoryLot> lots = List.of(older, newer);

        check(new FifoStrategy(), lots, "LOT-A");
        check(new LifoStrategy(), lots, "LOT-B");
        check(new FefoStrategy(), lots, "LOT-B");

        older.reserve();
        if (older.canPick()) throw new AssertionError("Reserved lot must not be pickable");
        System.out.println("STATE: " + older);
        check(new FifoStrategy(), lots, "LOT-B");
        older.release();
        older.quarantine();
        if (older.canPick()) throw new AssertionError("Quarantined lot must not be pickable");
        System.out.println("STATE: " + older);

        ObservableInventory inventory = new ObservableInventory();
        AuditObserver audit = new AuditObserver();
        inventory.subscribe(audit);
        inventory.subscribe(new LowStockObserver(5));
        inventory.adjust("Medicine", 10);
        inventory.adjust("Medicine", -6);
        if (inventory.stock("Medicine") != 4 || audit.getEvents().size() != 2) {
            throw new AssertionError("Observer check failed");
        }
        System.out.println("SUCCESS: State, Strategy and Observer checks passed.");
    }

    private static void check(PickingStrategy strategy, List<InventoryLot> lots, String expected) {
        List<InventoryLot> ranked = strategy.order(lots);
        if (ranked.isEmpty() || !expected.equals(ranked.get(0).getId())) {
            throw new AssertionError(strategy.name() + " selected an unexpected lot");
        }
        System.out.println("STRATEGY " + strategy.name() + ": " + ranked.get(0).getId());
    }
}
