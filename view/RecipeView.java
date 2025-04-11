package view;

import model.Recipe;
import model.Ingredient;

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
        String ret = ""+
        //"\n=== Recipe Details ==="+
        "__" + recipe.getDescription()+"__"+
        "\t  " + recipe.getDuration() + " seconds"+
        "\t  " + recipe.getPrice() + " gold"+
        "\t  " + recipe.getEquipment();

            
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            ret+="\n\t- " + entry.getKey().getName() + " x" + entry.getValue();
        }
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
