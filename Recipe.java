import java.util.Map;

public class Recipe {
    private Map<Ingredient, Integer> ingredients;
    private String description;
    private String equipment;

    public Recipe(Map<Ingredient, Integer> ingredients, String description, String equipment) {
        this.ingredients = ingredients;
        this.description = description;
        this.equipment = equipment;
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
