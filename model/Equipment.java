package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Equipment {
    private String name;
    private String type;
    private int slots;
    private double cookSpeedMultiplier;
    private int qualityBonus;

    private List<CookingTask> activeTasks;

    public Equipment(String name, String type, int slots, double cookSpeedMultiplier, int qualityBonus) {
        this.name = name;
        this.type = type;
        this.slots = slots;
        this.cookSpeedMultiplier = cookSpeedMultiplier;
        this.qualityBonus = qualityBonus;
        this.activeTasks = new ArrayList<>();
    }

    public boolean canCook() {
        return activeTasks.size() < slots;
    }

    public boolean startCooking(Recipe recipe, Player player, Player.CookingCallback callback) {
        if (!player.hasIngredients(recipe.getIngredients())) {
            return false;
        }

        if (!canCook()) {
            System.out.println(name + " is full!");
            return false;
        }

        player.useIngredients(recipe.getIngredients());

        long adjustedDuration = (long)(recipe.getDuration() * cookSpeedMultiplier);
        activeTasks.add(new CookingTask(recipe, adjustedDuration, player, callback));

        System.out.println("Started cooking " + recipe.getDescription() + " on " + name +
                " (Time: " + adjustedDuration + ")");
        return true;
    }

    public void updateCooking(long elapsedTime) {
        Iterator<CookingTask> iterator = activeTasks.iterator();

        while (iterator.hasNext()) {
            CookingTask task = iterator.next();
            task.reduceTime(elapsedTime);

            if (task.isFinished()) {
                Meal cookedMeal = new Meal(task.getRecipe(), 
                                           new java.util.Random().nextInt(101) + qualityBonus);
                task.getPlayer().addGold(cookedMeal.getFinalPrice());

                task.getCallback().onCookingCompleted(
                        "Cooked and sold: " + cookedMeal.getRecipe().getDescription() +
                        " for " + cookedMeal.getFinalPrice() + " gold using " + name
                );

                iterator.remove();
            }
        }
    }

    // Nested helper class for cooking jobs
    private static class CookingTask {
        private Recipe recipe;
        private long remainingTime;
        private Player player;
        private Player.CookingCallback callback;

        public CookingTask(Recipe recipe, long remainingTime, Player player, Player.CookingCallback callback) {
            this.recipe = recipe;
            this.remainingTime = remainingTime;
            this.player = player;
            this.callback = callback;
        }

        public void reduceTime(long elapsed) {
            this.remainingTime -= elapsed;
        }

        public boolean isFinished() {
            return remainingTime <= 0;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public Player getPlayer() {
            return player;
        }

        public Player.CookingCallback getCallback() {
            return callback;
        }
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", slots=" + slots +
                ", cookSpeedMultiplier=" + cookSpeedMultiplier +
                ", qualityBonus=" + qualityBonus +
                ", activeTasks=" + activeTasks.size() +
                '}';
    }
}
