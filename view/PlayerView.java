package view;

import java.util.Map;

import model.Ingredient;
import model.Player;

public class PlayerView {
    public String inventoryToString(Player player) {
        StringBuilder ret = new StringBuilder("\n=== Inventory ===\n");
        ret.append("Gold: ").append(player.getGold()).append("\n");
        if (player.getInventory().isEmpty()) {
            ret.append("No items in inventory.\n");
        }else{
            ret.append("Items:\n");
        for (Map.Entry<Ingredient, Integer> entry : player.getInventory().entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();
            ret.append("- ").append(ingredient.getName()).append(" x").append(quantity).append("\n");
        }
}
        ret.append("\n[0] Go back\n");
        ret.append("-----------\n");

        return ret.toString();
    }

    public String goldToString(Player player) {
        return "\nYour gold: " + player.getGold();
    }
}
