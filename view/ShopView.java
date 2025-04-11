package view;

import model.Ingredient;
import java.util.List;

public class ShopView {

    private ConsoleView console;

    public ShopView(ConsoleView console) {
        this.console = console;
    }

    public void printStock(List<Ingredient> stock) {
        console.print("\n=== Shop Items ===");
        if (stock.isEmpty()) {
            console.print("No items in stock.");
            return;
        }

        for (int i = 0; i < stock.size(); i++) {
            Ingredient ing = stock.get(i);
            console.print("[" + (i + 1) + "] " + ing.getName() + " - " + ing.getPrice() + " gold");
        }
    }

    public void displayPurchaseSuccess(Ingredient ing, int quantity) {
        console.print("Successfully bought " + quantity + " x " + ing.getName() + ".");
    }

    public void displayPurchaseFailure(String reason) {
        console.print("Could not complete purchase: " + reason);
    }
}
