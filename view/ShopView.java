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
    
        //  Compute max name length for padding
        int maxNameLen = 0;
        for (Ingredient ing : stock) {
            maxNameLen = Math.max(maxNameLen, ing.getName().length());
        }
    
        int total = stock.size();
        int cols = 3;
        int rows = (total + cols - 1) / cols;  // round up for 3 columns
    
        // 2 Helper format for each cell
        String cellFmt = "[%2d] %-" + maxNameLen + "s  %2d gold";
    
        // 3 Print each row with up to three cells
        for (int row = 0; row < rows; row++) {
            StringBuilder line = new StringBuilder();
    
            for (int col = 0; col < cols; col++) {
                int idx = row + col * rows;
                if (idx < total) {
                    Ingredient ing = stock.get(idx);
                    String cell = String.format(cellFmt, idx + 1, ing.getName(), ing.getPrice());
                    line.append(cell);
    
                    // Add extra spacing between columns
                    if (col < cols - 1) {
                        line.append("      ");  // 6 spaces between columns
                    }
                }
            }
    
            console.print(line.toString());
        }
    
        console.print("[ 0] Go back");
    }
    
    public void displayPurchaseSuccess(Ingredient ing, int quantity) {
        console.print("Successfully bought " + quantity + " x " + ing.getName() + ".");
    }

    public void displayPurchaseFailure(String reason) {
        console.print("Could not complete purchase: " + reason);
    }
}
