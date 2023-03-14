
package inventory.model;


/**
 * Clasa abstracta ce descrie o piesa
 */
public abstract class Part {

    // Declare fields
    private int partId;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    // Constructor
    public Part(int partId, String name, double price, int inStock, int min, int max) {
        this.partId = partId;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    
    // Getters

    /**
     * gets part id
     * @return int
     */
    public int getPartId() {
        return partId;
    }

    /**
     * gets name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * gets price
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * gets stock
     * @return int
     */
    public int getInStock() {
        return inStock;
    }

    /**
     * gets min
     * @return int
     */
    public int getMin() {
        return min;
    }

    /**
     * gets max
     * @return int
     */
    public int getMax() {
        return max;
    }
    
    // Setters

    /**
     * sets part id
     * @param partId int
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }

    /**
     * sets name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets price
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * sets stock
     * @param inStock int
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    /**
     * sets min
     * @param min int
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * sets max
     * @param max int
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * Generate an error message for invalid values in a part
     * Valid part will return an empty string
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param errorMessage
     * @return 
     */
    public static String isValidPart(String name, double price, int inStock, int min, int max, String errorMessage) {
        if(name.equals("")) {
            errorMessage += "A name has not been entered. ";
        }
        if(price < 0.01) {
            errorMessage += "The price must be greater than 0. ";
        }
        if(inStock < 0) {
            errorMessage += "Inventory level must be greater than 0. ";
        }
        if(min > max) {
            errorMessage += "The Min value must be less than the Max value. ";
        }
        if(inStock < min) {
            errorMessage += "Inventory level is lower than minimum value. ";
        }
        if(inStock > max) {
            errorMessage += "Inventory level is higher than the maximum value. ";
        }
        return errorMessage;
    }
    @Override
    public String toString() {
        return this.partId+","+this.name+","+this.price+","+this.inStock+","+
                this.min+","+this.max;
    }
}