/* Professor: Amal Ibrahim
 * Program by: Aidan Salagala
 * Due by: 2025-04-06
 * Course: CST8284_302
 */
package assn3;

import java.util.List;
import java.util.Scanner;

/**
 * RecipeManagerTester.java
 * To give a menu and do certain actions based on user input
 * @author Aidan Salagala
 * @version 1
 * @see assn3
 * @since 21
 * */
public class RecipeManagerTester {

	/**Default constructor*/
	public RecipeManagerTester() {}
	
	/**Main to display output and gain input
	 * @param args main method*/
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager();
        Scanner scanner = new Scanner(System.in);

        manager.loadRecipes();
        List<Recipe> recipes = manager.getRecipes();
        int[] quantities = new int[recipes.size()];

        System.out.println("Welcome to Aidan Salagala's recipe manager.");
        
        while (true) {
        	manager.printMenu();

            System.out.print("Please enter your choice: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 4) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only type digits.");
                System.out.println("Valid input are digits from 0 to 4.");
                continue;
            }

            switch (choice) {
                case 0:
                    break;

                case 1:
                    System.out.println("Available Recipes:");
                    for (int i = 0; i < recipes.size(); i++) {
                        System.out.println((i + 1) + ". " + recipes.get(i).getName());
                    }
                    break;

                case 2:
                    int recipeNumber = -1;
                    while (recipeNumber == -1) {
                        System.out.print("Which bread would you like? ");
                        try {
                            recipeNumber = Integer.parseInt(scanner.nextLine());
                            if (recipeNumber < 1 || recipeNumber > 7) {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please only type digits. Valid input are digits from 1 to 7.");
                            recipeNumber = -1;
                        }
                    }

                    int quantity = 0;
                    while (true) {
                        System.out.print("How much of this bread would you like? ");
                        try {
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity == -1) {
                                if (quantities[recipeNumber - 1] > 0) {
                                    quantities[recipeNumber - 1] -= 1;
                                } else {
                                    System.out.println("There is already 0 loaves for this bread");
                                }
                                break;
                            } else {
                                quantities[recipeNumber - 1] += quantity;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please only type digits.");
                        }
                    }
                    break;




                case 3:
                    String shopList = manager.generateShopList(recipes, quantities);

                    if (shopList.isEmpty()) {
                        System.out.println("No orders placed yet!");
                    } else {
                        System.out.println(shopList);
                        System.out.print("Do you want to save this list (Y/n)? ");
                        String saveOption = scanner.nextLine();
                        if (saveOption.equalsIgnoreCase("Y")) {
                            manager.saveShopList(shopList); 
                        }
                    }
                    break;


                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }
}
