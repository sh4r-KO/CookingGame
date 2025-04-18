package view;

import model.Recipe;
import model.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RecipeView {

    public String recipeToString(Recipe recipe) {
        StringBuilder sb = new StringBuilder();

        // First line: description
        sb.append(recipe.getDescription()).append("\n");

        // Second line: metadata
        sb.append("    ")
                .append(recipe.getDuration()).append("s  ")
                .append(recipe.getPrice()).append(" gold  ")
                .append(recipe.getEquipment()).append("\n");

        // Ingredients
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            sb.append("    - ")
                    .append(entry.getValue()).append(" ")
                    .append(entry.getKey().getName()).append("\n");
        }

        return sb.toString().trim(); // trim to remove extra trailing newline
    }

    public String recipeListToString(List<Recipe> recipes) {
        StringBuilder sb = new StringBuilder("\n=== Recipes ===\n");
    
        if (recipes.isEmpty()) {
            sb.append("No recipes available.\n");
            return sb.toString();
        }
    
        // Build each full recipe string
        List<String> recipeBlocks = new ArrayList<>();
        int maxIndexDigits = String.valueOf(recipes.size()).length();
        String indexFormat = "[%" + maxIndexDigits + "d] ";
    
        for (int i = 0; i < recipes.size(); i++) {
            String header = String.format(indexFormat, i + 1);
            String body = recipeToString(recipes.get(i));
            String indent = new String(new char[header.length()]).replace('\0', ' ');
            recipeBlocks.add(header + body.replace("\n", "\n" + indent));
        }
    
        // Calculate layout
        int colSpacing = 6;
        int colCount = 3;
        int rows = (int) Math.ceil(recipeBlocks.size() / (double) colCount);
    
        // Get max width per block for alignment
        int maxWidth = 0;
        for (String block : recipeBlocks) {
            String[] lines = block.split("\n");
            for (String line : lines) {
                maxWidth = Math.max(maxWidth, line.length());
            }
        }
    
        // Build the 3-column display
        for (int row = 0; row < rows; row++) {
            String[] columns = new String[colCount];
            for (int col = 0; col < colCount; col++) {
                int index = row + col * rows;
                columns[col] = (index < recipeBlocks.size()) ? recipeBlocks.get(index) : "";
            }
    
            // Merge the lines of the columns
            String[][] columnLines = new String[colCount][];
            int maxLines = 0;
            for (int i = 0; i < colCount; i++) {
                columnLines[i] = columns[i].split("\n");
                maxLines = Math.max(maxLines, columnLines[i].length);
            }
    
            for (int line = 0; line < maxLines; line++) {
                for (int col = 0; col < colCount; col++) {
                    String content = (line < columnLines[col].length) ? columnLines[col][line] : "";
                    sb.append(String.format("%-" + (maxWidth + colSpacing) + "s", content));
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
    
        //sb.append("[ 0] Go back\n-----------");
        return sb.toString();
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
