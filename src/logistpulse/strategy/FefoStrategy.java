package logistpulse.strategy;

import logistpulse.state.InventoryLot;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class FefoStrategy implements PickingStrategy {
    public String name() { return "FEFO"; }
    public List<InventoryLot> order(List<InventoryLot> lots) {
        return lots.stream().filter(InventoryLot::canPick)
            .sorted(Comparator.comparing(InventoryLot::getExpiresAt).thenComparing(InventoryLot::getId))
            .collect(Collectors.toList());
    }
}
