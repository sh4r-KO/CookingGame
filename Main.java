import java.util.*;
import javafx.util.Pair;
import model.Ingredient;
import model.Meal;
import model.Player;
import model.Recipe;
import model.RecipeTimer;
import model.Shop;
import view.*;



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
        recipes.add(new Recipe(ingredients, "Tomato Melt", "Panini Press",3,5));

        ingredients = new HashMap<Ingredient, Integer>() {
            { put(Meat, 2); }
            { put(Spice, 1); }
        };
        recipes.add(new Recipe(ingredients, "Spicy Steak","Grill",3,10));

        // Shop stock



        Shop shop = new Shop(Arrays.asList(Tomato,Cheese,Meat,Spice));
        ConsoleView cView = new ConsoleView();
        ShopView sView = new ShopView(cView);
        RecipeView rview = new RecipeView();
        while (true) {
            System.out.print("\033[H\033[2J");//todelete if things dont show up
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
                        System.out.println("[" + (i + 1) + "] " + rview.recipeToString(recipe)+"\n");
                    }
                    System.out.println("[0] Go back");
                    System.out.println("-----------");

                    int rChoice = Integer.parseInt(scanner.nextLine()) - 1;
                    if (rChoice == -1)break; // Go back
                    if (rChoice >= 0 && rChoice < recipes.size()) {
                        Recipe chosen = recipes.get(rChoice);
                        if (player.hasIngredients(chosen.getIngredients())) {
                            player.useIngredients(chosen.getIngredients());
                            System.out.println("Cooking " + chosen.getDescription() + "... it will take 3 seconds.");
                        
                            RecipeTimer timer = new RecipeTimer();
                            timer.startTimer(() -> {
                                // This code is executed after 3 seconds
                                Meal meal = new Meal(chosen, new Random().nextInt(101)); // Random quality
                                player.addGold(meal.getFinalPrice());
                                System.out.println("Cooked and sold: " + meal.getRecipe().getDescription()+"\t for "+meal.getFinalPrice()+"gold");
                                // You may want to also update any UI, inform the player, etc.
                            },(chosen.getDuration()*1000) );
                        } else {
                            System.out.println("Not enough ingredients!");
                        }
                    }
                    try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

                    break;

                case "2": // Buy
                    while (true) {
                        System.out.print("\033[H\033[2J");//todelete if things dont sho up
                        System.out.flush();                 //as well
                        System.out.println("\n-----------");
                        System.out.println("\nYour gold: " + player.getGold());
                        //shop.printStock();
                        sView.printStock(shop.getStock());
                        
                        System.out.println("[0] Go back");
                        System.out.print("Choose item number to buy: ");
                        System.out.println("\n-----------");
                        int bChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        System.out.print("how much :");

                        if (bChoice == -1)break; // Go back
                        if (bChoice >= 0 && bChoice < shop.size()) {
                            //player.buy()
                            int bChoiceqty = Integer.parseInt(scanner.nextLine());
                            System.out.print("passing order..");

                            Pair<Ingredient,Integer> tmp2 =  shop.buy(bChoice,bChoiceqty);
                            int qty = tmp2.getValue();
                            Ingredient tmp = tmp2.getKey();
                            if (player.getGold()>=(tmp.getPrice()*qty)) {
                                player.addIngredient(tmp,(1*qty));
                                
                                player.spendGold((tmp.getPrice()*qty));
                                System.out.println("Item(s) bought!");
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
                    System.out.println("\nYour gold: " + player.getGold());

                    player.printInventory();
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
