import java.util.List;

public class Shop {
    private List<Ingredient> stock;

    public Shop(List<Ingredient> stock) {
        this.stock = stock;
    }

    public void showStock() {
        System.out.println("Shop items:");
        for (int i = 0; i < stock.size(); i++) {
            Ingredient ing = stock.get(i);
            System.out.println("[" + (i + 1) + "] " + ing.getName() + " x" + ing.getQuantity() + " - 1 gold each");
        }
    }

    public Ingredient buy(int index) {
        if (index < 0 || index >= stock.size()) return null;
        Ingredient item = stock.get(index);
        return new Ingredient(item.getName()); // Buy 1 unit
    }

    public int getPrice() {
        return 1; // Flat price for simplicity
    }

    public int size() {
        return stock.size();
    }
}
