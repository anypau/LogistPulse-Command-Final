package logistpulse.commands;

import logistpulse.inventory.Inventory;

public class AddStockCommand implements Command {
    private final Inventory inventory;
    private final String product;
    private final int quantity;

    public AddStockCommand(Inventory inventory, String product, int quantity) {
        this.inventory = inventory;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        inventory.addStock(product, quantity);
    }

    @Override
    public void undo() {
        inventory.removeStock(product, quantity);
    }

    @Override
    public String toString() {
        return "Add " + quantity + " units of " + product;
    }
}
