package logistpulse;

import logistpulse.commands.AddStockCommand;
import logistpulse.commands.RemoveStockCommand;
import logistpulse.inventory.Inventory;
import logistpulse.manager.CommandManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== LOGISTPULSE SMART INVENTORY ===");
        Inventory inventory = new Inventory();
        CommandManager manager = new CommandManager();

        inventory.addStock("Laptop", 50);
        inventory.addStock("Mouse", 20);
        inventory.showInventory();

        manager.enqueue(new RemoveStockCommand(inventory, "Laptop", 10));
        manager.enqueue(new AddStockCommand(inventory, "Laptop", 15));
        manager.enqueue(new AddStockCommand(inventory, "Mouse", 5));
        manager.postpone();
        manager.executeAll();
        manager.restorePostponed();
        manager.executeAll();
        manager.undoLast();
        inventory.showInventory();
        manager.showLog();

        if (inventory.getStock("Laptop") != 65 || inventory.getStock("Mouse") != 25) {
            throw new AssertionError("Unexpected final stock");
        }
        System.out.println("\nSUCCESS: All inventory checks passed.");
    }
}
