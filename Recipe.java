/* Professor: Amal Ibrahim
 * Program by: Aidan Salagala
 * Due by: 2025-04-06
 * Course: CST8284_302
 */
package assn3;

/**
 * Recipe.java
 * To store info with setters and for other files to access with getters
 * @author Aidan Salagala
 * @version 1
 * @see assn3
 * @since 21
 * */
public class Recipe {
	
	/**Default constructor*/
	public Recipe () {}
 	
	/**variable to hold recipe name*/
	private String name;
	/**variable to hold eggs for recipe*/
    private float eggs;
    /**variable to hold yeast for recipe*/
    private float yeast;
    /**variable to hold flour for recipe*/
    private float flour;
    /**variable to hold sugar for recipe*/
    private float sugar;
    /**variable to hold butter for recipe*/
    private float butter;
    
    /**returns name variable
     * @return name*/
	public String getName() {
		return name;
	}
	/**records name variable
	 * @param name name*/
	public void setName(String name) {
		this.name = name;
	}
	/**returns eggs variable
	 * @return eggs*/
	public float getEggs() {
		return eggs;
	}
	/**records eggs variable
	 * @param eggs eggs*/
	public void setEggs(float eggs) {
		this.eggs = eggs;
	}
	/**returns yeast variable
	 * @return yeast*/
	public float getYeast() {
		return yeast;
	}
	/**records yeast variable
	 * @param yeast yeast*/
	public void setYeast(float yeast) {
		this.yeast = yeast;
	}
	/**return flour variable
	 * @return flour*/
	public float getFlour() {
		return flour;
	}
	/**records flour variable
	 * @param flour flour*/
	public void setFlour(float flour) {
		this.flour = flour;
	}
	/**returns sugar variable 
	 * @return sugar */
	public float getSugar() {
		return sugar;
	}
	/**records sugar variable
	 * @param sugar sugar*/
	public void setSugar(float sugar) {
		this.sugar = sugar;
	}
	/**returns butter variable
	 * @return butter*/
	public float getButter() {
		return butter;
	}
	/**records butter variable
	 * @param butter butter*/
	public void setButter(float butter) {
		this.butter = butter;
	}
    
}
	
	
