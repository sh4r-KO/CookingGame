package view;

import java.util.Scanner;

import model.Player;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public int getInputInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next(); // consume the non-int input
        }
        return scanner.nextInt();
    }

    public String getInputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");//todelete if things dont sho up
        System.out.flush();                 //as well
    }

    public void displayRecipeList(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayRecipeList'");
    }
}
