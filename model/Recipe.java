package model;
import java.util.Map;

public class Recipe {
    private Map<Ingredient, Integer> ingredients;
    private String description;
    private String equipment;
    private long duration;
    private int price;


    public Recipe(Map<Ingredient, Integer> ingredients, String description, String equipment,long duration,int price) {
        this.ingredients = ingredients;
        this.description = description;
        this.equipment = equipment;
        this.duration = duration;
        this.price = price;
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
