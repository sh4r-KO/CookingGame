package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    public boolean buy() {
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

    public boolean cook(Recipe recipe, CookingCallback callback) {
        if (hasIngredients(recipe.getIngredients())) {
            useIngredients(recipe.getIngredients());

            RecipeTimer timer = new RecipeTimer();
            timer.startTimer(() -> {
                Meal meal = new Meal(recipe, new Random().nextInt(101)); // Random quality
                this.addGold(meal.getFinalPrice());
                // Notify via the callback once cooking is complete.
                callback.onCookingCompleted("Cooked and sold: " +
                        meal.getRecipe().getDescription() +
                        " for " + meal.getFinalPrice() + " gold");
            }, recipe.getDuration() * 1000);
            return true;
        }
        return false;
    }

    public interface CookingCallback {// maybe move this to Playerview?
        void onCookingCompleted(String message);
    }

    public Map<Ingredient, Integer> getInventory() {
        return inventory;
    }

    // a method that will extract a csv file and return a map of ingredients and
    // their quantities
    public void loadInventoryFromCSV(String filePath) {
        // Implement CSV loading logic here

        // For now, just a placeholder
        System.out.println("Loading inventory from " + filePath);
    }

    public void saveInventoryToCSV(String filePath) {
        // Implement CSV saving logic here

        // For now, just a placeholder
        System.out.println("Saving inventory to " + filePath);
    }

}
