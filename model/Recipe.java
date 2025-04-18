package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {
    private Map<Ingredient, Integer> ingredients;
    private String description;
    private String equipment;
    private long duration;
    private int price;

    public Recipe(Map<Ingredient, Integer> ingredients, String description, String equipment, long duration,
            int price) {
        this.ingredients = ingredients;
        this.description = description;
        this.equipment = equipment;
        this.duration = duration;
        this.price = price;
    }

    public Recipe() {

    }

    public List<Recipe> ListreadRecipeFromCsv() {
        List<Recipe> recipes = new ArrayList<>();
        String filePath = "data/recipes.csv"; // Update path
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
    
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // skip header
                    continue;
                }
    
                String[] tokens = line.split(",");
                Map<Ingredient, Integer> ingredients = new HashMap<>();
    
                // Helper loop to process ingredients in sets of 3 (Name, Quality, Quantity)
                for (int i = 0; i <= 9; i += 3) {
                    if (!tokens[i].isEmpty()) {
                        Ingredient newIngredient = new Ingredient(tokens[i], parseIntSafe(tokens[i + 1]));
                        int quantity = parseIntSafe(tokens[i + 2]);
    
                        boolean merged = false;
                        for (Ingredient existing : ingredients.keySet()) {
                            if (existing.equals(newIngredient)) {
                                ingredients.put(existing, ingredients.get(existing) + quantity);
                                merged = true;
                                break;
                            }
                        }
                        if (!merged) {
                            ingredients.put(newIngredient, quantity);
                        }
                    }
                }
    
                String description = tokens[12];  // Description
                String equipment = tokens[13];    // Equipment
                long duration = Long.parseLong(tokens[14]);  // Duration
                int reward = Integer.parseInt(tokens[15]);   // Reward
    
                Recipe recipe = new Recipe(ingredients, description, equipment, duration, reward);
                recipes.add(recipe);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipes;
    }
    

    private int parseIntSafe(String str) {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getEquipment() {
        return equipment;
    }

    public long getDuration() {
        return duration;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe{ingredients=");
        for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            sb.append(entry.getKey()).append(" x").append(entry.getValue()).append(", ");
        }
        sb.append("description='").append(description).append('\'');
        sb.append(", equipment='").append(equipment).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
