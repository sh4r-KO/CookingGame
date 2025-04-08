import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(10);

        //ingredients for recipes
        Ingredient Tomato = new Ingredient("Tomato",1);
        Ingredient Cheese = new Ingredient("Cheese",2);
        Ingredient Meat = new Ingredient("Meat",3);
        Ingredient Spice = new Ingredient("Spice",2);

        // Sample recipes
        List<Recipe> recipes = new ArrayList<>();

        Map<Ingredient, Integer> ingredients = new HashMap<Ingredient, Integer>() {
            { put(Tomato, 2); }
            { put(Cheese, 1); }
        };
        recipes.add(new Recipe(ingredients, "Tomato Melt", "Panini Press"));

        ingredients = new HashMap<Ingredient, Integer>() {
            { put(Meat, 2); }
            { put(Spice, 1); }
        };
        recipes.add(new Recipe(ingredients, "Spicy Steak","Grill"));

        // Shop stock



        Shop shop = new Shop(Arrays.asList(Tomato,Cheese,Meat,Spice));

        while (true) {
            System.out.print("\033[H\033[2J");//todelete if things dont sho up
            System.out.flush();                 //as well


            System.out.println("\n-----------");
            System.out.println("\nYour gold: " + player.getGold());
            System.out.println("Choose action: [1] Cook  [2] Buy  [3] Show Inventory  [0] Exit");
            System.out.println("\n-----------");

            String input = scanner.nextLine();

            switch (input) {
                case "1": // Cook
                    System.out.print("\033[H\033[2J");//todelete if things dont sho up
                    System.out.flush();                 //as well
                    System.out.println("\n-----------");
                    System.out.println("Choose a recipe:");
                    for (int i = 0; i < recipes.size(); i++) {
                        Recipe recipe = recipes.get(i);
                        System.out.println("[" + (i + 1) + "] " + recipe.getDescription());
                        System.out.print("    Requires: ");
                        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
                            Ingredient ing = entry.getKey();
                            Integer qty = entry.getValue();
                            System.out.print(ing + " x" + qty + " ");
                        }
                        System.out.println("\n    Equipment: " + recipe.getEquipment());
                    }
                    System.out.println("[0] Go back");
                    System.out.println("\n-----------");

                    int rChoice = Integer.parseInt(scanner.nextLine()) - 1;
                    if (rChoice == -1)
                        break; // Go back
                    if (rChoice >= 0 && rChoice < recipes.size()) {
                        Recipe chosen = recipes.get(rChoice);
                        if (player.hasIngredients(chosen.getIngredients())) {
                            player.useIngredients(chosen.getIngredients());
                            Meal meal = new Meal(chosen, new Random().nextInt(101)); // Random quality
                            player.addGold(meal.getFinalPrice());
                            System.out.println("Cooked and sold: " + meal);
                        } else {
                            System.out.println("Not enough ingredients!");
                        }
                    }
                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

                    break;

                case "2": // Buy
                    while (true) {
                        System.out.print("\033[H\033[2J");//todelete if things dont sho up
                        System.out.flush();                 //as well
                        System.out.println("\n-----------");
                        shop.showStock();
                        System.out.println("[0] Go back");
                        System.out.print("Choose item number to buy: ");
                        System.out.println("\n-----------");

                        int bChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        if (bChoice == -1)
                            break; // Go back
                        if (bChoice >= 0 && bChoice < shop.size()) {
                            Ingredient tmp = shop.buy(bChoice);
                            if (player.getGold()>=tmp.getPrice()) {
                                player.addIngredient(tmp,1);
                                
                                player.spendGold(tmp.getPrice());
                                System.out.println("Item bought!");
                            } else {
                                System.out.println("Not enough gold!");
                            }
                        } else {
                            System.out.println("Invalid choice.");
                        }
                        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                    }
                    
                    break;


                case "3": // Show inventory
                    System.out.println("\n-----------");

                    player.showInventory();
                    System.out.println("[0] Go back");

                    int goback = Integer.parseInt(scanner.nextLine()) - 1;
                    if(goback==0){
                        System.out.println("\n-----------");
                        break;
                    }                    
                    System.out.println("\n-----------");

                    break;

                case "0": // Exit
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid input!");
            }
        }

    }

}
