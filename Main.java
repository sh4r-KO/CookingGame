import java.util.*;
import javafx.util.Pair;

import model.*;

import view.*;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(10);

        //ingredients for recipes
        //Ingredient Tomato = new Ingredient("Tomato",1);


        // Sample recipes
        Recipe r = new Recipe();
        List<Recipe> recipes  = r.ListreadRecipeFromCsv();

        List<Ingredient> stock = new ArrayList<>();

        for (Recipe recipe : recipes) {
            stock.addAll(recipe.getIngredients().keySet());
        }
        //TODO: check double ingredients in stock/recipes. a recipe can have the same ingredient multiple times which are 2 differnet java objects can cause some issues
        Shop shop = new Shop(stock);
        shop.deleteDuplicate();


        /*List<Recipe> recipes = new ArrayList<>();

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

        */

        // Shop stock



        //Shop shop = new Shop(Arrays.asList(Tomato,Cheese,Meat,Spice));
        ConsoleView cView = new ConsoleView();
        ShopView sView = new ShopView(cView);
        RecipeView rview = new RecipeView();
        PlayerView playerView = new PlayerView();

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
                    cView.clearScreen();

                    System.out.println(rview.recipeListToString(recipes));
                    System.out.println(playerView.inventoryToString(player));

                    int rChoice = Integer.parseInt(scanner.nextLine()) - 1;
                    if (rChoice == -1)
                        break; // Go back
                    
                    if (rChoice >= 0 && rChoice < recipes.size()) { 
                        Recipe chosen = recipes.get(rChoice);
                        
                        boolean cookingStarted = player.cook(chosen, message -> {                        // Start the cooking process with a callback to display the completion message.
                            System.out.println(message);                            // This code will be executed when the cooking timer finishes.
                        });

                        
                        
                        if (cookingStarted) {
                            System.out.println("Cooking " + chosen.getDescription() + "... it will take " + chosen.getDuration() + " seconds.");
                        } else {
                            System.out.println("Not enough ingredients!");
                        }
                    }
                    
                    try {
                        Thread.sleep(500); // Allow time for the message to be visible
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;


                case "2": // Buy
                    while (true) {
                        cView.clearScreen();
                        System.out.println(playerView.goldToString(player));
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

                    System.out.print("\033[H\033[2J");//todelete if things dont sho up
                    System.out.flush();                 //as well
                    System.out.println(playerView.inventoryToString(player));

                    int goback = Integer.parseInt(scanner.nextLine()) - 1;
                    if(goback==0){
                        break;
                    }

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
