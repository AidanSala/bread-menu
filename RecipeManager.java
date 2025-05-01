/* Professor: Amal Ibrahim
 * Program by: Aidan Salagala
 * Due by: 2025-04-06
 * Course: CST8284_302
 */
package assn3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * RecipeManager.java
 * To read file store info recipe and write to file based on choices user makes in recipemanagertester
 * @author Aidan Salagala
 * @version 1
 * @see assn3
 * @since 21
 * */
public class RecipeManager {
	/**list to store recipes from file*/
    private List<Recipe> recipes;

    /**intializes list to store recipes*/
    public RecipeManager() {
        this.recipes = new ArrayList<>();
    }

    /**reads the file with conditions as to what makes a recipe and adds to arraylist*/
    public void loadRecipes() {
        try (FileReader reader = new FileReader("recipelist.txt")) {
            Recipe recipe = null;
            String line = "";
            int character;

            while ((character = reader.read()) != -1) {
                char currentChar = (char) character;

                if (currentChar == '\n' || currentChar == '\r') {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        if (line.startsWith("Recipe")) {
                            if (recipe != null) {
                                recipes.add(recipe);
                            }
                            recipe = new Recipe();
                            recipe.setName(line.substring(7).trim());
                        } else if (recipe != null) {
                            parseIngredientLine(line, recipe);
                        }
                    }
                    line = ""; 
                } else {
                    line += currentChar;
                }
            }

           
            if (recipe != null) {
                recipes.add(recipe);
            }

        } catch (FileNotFoundException e) {
            System.err.println("recipelist.txt not found.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

/**converts the string ingredients from file to float
 * @param line variable containing ingredient and its amount
 * @param recipe object to set parsed ingredient amount to recipe class*/
    private void parseIngredientLine(String line, Recipe recipe) {
        try {
            String[] parts = line.split("\\s+");
            float value = Float.parseFloat(parts[1]);

            switch (parts[0].toLowerCase()) {
                case "eggs":
                    recipe.setEggs(value);
                    break;
                case "yeast":
                    recipe.setYeast(value);
                    break;
                case "flour":
                    recipe.setFlour(value);
                    break;
                case "sugar":
                    recipe.setSugar(value);
                    break;
                case "butter":
                    recipe.setButter(value);
                    break;
                default:
                    System.err.println("Unknown ingredient: " + parts[0]);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid format in line: " + line);
        }
    }


    /**method to save info for recipe and amount of bread they want. Also won't show ingredients that are 0
     * all in string shoplist variable
     * @param recipes what recipe they want
     * @param quantities amount of recipe they want
     * @return shopList variable with info of ingredients based on bread*/
    public String generateShopList(List<Recipe> recipes, int[] quantities) {
        String shopList = ""; 
        boolean hasOrders = false;

        float totalYeast = 0, totalFlour = 0, totalSugar = 0, totalEggs = 0, totalButter = 0;

        for (int i = 0; i < recipes.size(); i++) {
            if (quantities[i] > 0) {
                hasOrders = true;
                shopList += quantities[i] + " " + recipes.get(i).getName() + " loaf/loaves.\n";

                totalYeast += recipes.get(i).getYeast() * quantities[i];
                totalFlour += recipes.get(i).getFlour() * quantities[i];
                totalSugar += recipes.get(i).getSugar() * quantities[i];
                totalEggs += recipes.get(i).getEggs() * quantities[i];
                totalButter += recipes.get(i).getButter() * quantities[i];
            }
        }

        if (hasOrders) {
            shopList += "\nYou will need a total of:\n";
            if (totalYeast > 0) shopList += totalYeast + " grams of yeast\n";
            if (totalFlour > 0) shopList += totalFlour + " grams of flour\n";
            if (totalSugar > 0) shopList += totalSugar + " grams of sugar\n";
            if (totalEggs > 0) shopList += (int) totalEggs + " egg(s)\n"; 
            if (totalButter > 0) shopList += totalButter + " grams of butter\n"; 
        }

        return shopList.trim();
    }

    /**Method to display the menu*/
    public void printMenu() {
    	System.out.println("Please select one of the following options:");
        System.out.println("1. Show available recipes.");
        System.out.println("2. Create Shop List.");
        System.out.println("3. Print Shop List.");
        System.out.println("4. Quit Program.");
        System.out.println("0. to reprint this menu.");
    }

  
    /**method to make and write to shoppinglist.txt with information from generateShopList method through shoplist variable
     * @param shopList variable with info from generateShopList method*/
    public void saveShopList(String shopList) {
        try (FileWriter writer = new FileWriter("shoppinglist.txt")) {
            writer.write(shopList);
        } catch (IOException e) {
            System.err.println("Error saving shop list: " + e.getMessage());
        }
    }

    /**method to show case all recipes
     * @return recipes contains all the recipes*/
    public List<Recipe> getRecipes() {
        return recipes;
    }
    
}
