import java.util.HashMap;
import java.util.Map;

public class Player {
    private int gold;
    private Map<Ingredient, Integer> inventory;

    public Player(int startingGold) {
        this.gold = startingGold;
        this.inventory = new HashMap<>();
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }
    public void subGold(int amount) {
        gold -= amount;
    }

    public boolean spendGold(int amount) {
        if (this.gold >= amount) {
            this.gold -= amount;
            return true;
        }
        return false;
    }

    public void addIngredient(Ingredient ing, int quantity) {
        inventory.put(ing, (inventory.getOrDefault(ing, 0) + quantity));
    }

    public boolean hasIngredients(Map<Ingredient, Integer> required) {
        for (Map.Entry<Ingredient, Integer> entry : required.entrySet()) {
            int ownedQty = inventory.getOrDefault(entry.getKey(), 0);
            if (ownedQty < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
    
    

    public void useIngredients(Map<Ingredient, Integer> required) {
        for (Map.Entry<Ingredient, Integer> entry : required.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int requiredQty = entry.getValue();
            
            int currentQty = inventory.getOrDefault(ingredient, 0);
            inventory.put(ingredient, currentQty - requiredQty);
        }
    
        // Remove ingredients with 0 or less
        inventory.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }
    

    public void showInventory() {
        System.out.println("Inventory:");
        if (inventory.isEmpty()) {
            System.out.println("- empty -");
        } else {
            for (Map.Entry<Ingredient, Integer> entry : inventory.entrySet()) {
                System.out.println("- " + entry.getKey() + " x" + entry.getValue());
            }
        }
    }

}
