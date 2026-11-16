package logistpulse.commands;

import logistpulse.inventory.Inventory;

public class RemoveStockCommand implements Command {
    private final Inventory inventory;
    private final String product;
    private final int quantity;

    public RemoveStockCommand(Inventory inventory, String product, int quantity) {
        this.inventory = inventory;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        inventory.removeStock(product, quantity);
    }

    @Override
    public void undo() {
        inventory.addStock(product, quantity);
    }

    @Override
    public String toString() {
        return "Remove " + quantity + " units of " + product;
    }
}
