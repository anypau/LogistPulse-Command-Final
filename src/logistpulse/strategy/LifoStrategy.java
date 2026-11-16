package logistpulse.strategy;

import logistpulse.state.InventoryLot;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class LifoStrategy implements PickingStrategy {
    public String name() { return "LIFO"; }
    public List<InventoryLot> order(List<InventoryLot> lots) {
        return lots.stream().filter(InventoryLot::canPick)
            .sorted(Comparator.comparing(InventoryLot::getReceivedAt).reversed().thenComparing(InventoryLot::getId))
            .collect(Collectors.toList());
    }
}
