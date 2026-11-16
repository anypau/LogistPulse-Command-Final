package logistpulse.strategy;

import logistpulse.state.InventoryLot;
import java.util.List;

public interface PickingStrategy {
    String name();
    List<InventoryLot> order(List<InventoryLot> lots);
}
