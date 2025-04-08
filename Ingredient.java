public class Ingredient {
    private String name;
    private int price;



    public Ingredient(String name,int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return name;
    }

    // These make the Map work properly
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ingredient)) return false;
        Ingredient other = (Ingredient) obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
