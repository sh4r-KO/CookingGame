import java.util.Random;

public class Meal {
    private Recipe recipe;
    private int quality; // 0–1000 maybe?
    private int finalPrice;
    private int minPrice = 5;
    private int maxPrice = 10;

    public Meal(Recipe recipe, int quality) {
        this.recipe = recipe;
        this.quality = quality;
        this.finalPrice = calculatePrice();
    }

    private int calculatePrice() {
        Random rand = new Random();
        int basePrice = rand.nextInt((maxPrice - minPrice) + 1) + minPrice;

        return basePrice ;//+ (quality / 20); // +0 to +5
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "recipe=" + recipe +
                ", price=" + finalPrice + "po" +
                ", quality=" + quality +
                '}';
    }
}
