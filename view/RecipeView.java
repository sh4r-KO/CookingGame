package view;

import model.Recipe;
import model.Ingredient;

import java.util.List;
import java.util.Map;

public class RecipeView {

    public void displayRecipe(Recipe recipe) {
        System.out.println("\n=== Recipe Details ===");
        System.out.println("\n  " + recipe.getDescription());
        System.out.println("\n  " + recipe.getEquipment());
        System.out.println("\n  " + recipe.getDuration() + " seconds");
        System.out.println("\n  " + recipe.getPrice() + " gold");

        System.out.println("\n  Ingredients:");
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            System.out.println("- " + entry.getKey().getName() + " x" + entry.getValue());
        }
    }

    public String recipeToString(Recipe recipe) {
        String ret = "" +
        // "\n=== Recipe Details ==="+
                "__" + recipe.getDescription() + "__" +
                "\t  " + recipe.getDuration() + " seconds" +
                "\t  " + recipe.getPrice() + " gold" +
                "\t  " + recipe.getEquipment();

        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            ret += "\n\t- " + entry.getKey().getName() + " x" + entry.getValue();
        }
        return ret;
    }

    public String recipeListToString(List<Recipe> recipes) {

        String ret = "" +
                "\n=== Recipes ===\n";
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            ret += "[" + (i + 1) + "] " + this.recipeToString(recipe)+"\n";
            //System.out.println("[" + (i + 1) + "] " + rview.recipeToString(recipe)+"\n");
        }
        ret+="[0] Go back"+
        "\n-----------";
        return ret;
    }

    public void displayRecipeList(Iterable<Recipe> recipes) {
        System.out.println("\n=== Available Recipes ===");
        int i = 1;
        for (Recipe recipe : recipes) {
            System.out.println("[" + i + "] " + recipe.getDescription());
            i++;
        }
    }

    public void displayNoRecipesAvailable() {
        System.out.println("No recipes available.");
    }
}
