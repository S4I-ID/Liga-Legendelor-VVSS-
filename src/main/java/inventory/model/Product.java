
package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Clasa ce descrie un produs
 */
public class Product {
    
    // Declare fields
    private ObservableList<Part> associatedParts;
    private int productId;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    // Constructor
    public Product(int productId, String name, double price, int inStock, int min, int max, ObservableList<Part> partList) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.associatedParts= partList;
    }
    
    // Getters

    /**
     * gets associated parts
     * @return ObservableList(Part)
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * gets id
     * @return int
     */
    public int getProductId() {
        return productId;
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
     * gets in stock
     * @return int
     */
    public int getInStock() {
        return inStock;
    }

    /**
     * gets min
     * @return min
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
     * sets associated parts
     * @param associatedParts ObservableList(Part)
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        // unsupported operation
    }

    /**
     * sets product id
     * @param productId int
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Sets name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets price
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * sets in stock
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

    public void setMax(int max) {
        this.max = max;
    }
    
    // Other methods

    /**
     * add associated part
     * @param part Part
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Removes associated part
     * @param part Part
     */
    public void removeAssociatedPart(Part part) {
        associatedParts.remove(part);
    }

    /**
     * Search for associated part
     * @param searchItem String
     * @return Part
     */
    public Part lookupAssociatedPart(String searchItem) {
        for(Part p:associatedParts) {
            if(p.getName().contains(searchItem) || Integer.toString(p.getPartId()).equals(searchItem)) return p;
        }
        return null;
    }
    
    /**
     * Generate an error message for invalid values in a product
     * and evaluate whether the sum of the price of associated parts
     * is less than the price of the resulting product.
     * A valid product will return an empty error message string.
     * @param name
     * @param min
     * @param max
     * @param inStock
     * @param price
     * @param parts
     * @param errorMessage
     * @return 
     */
    public static String isValidProduct(String name, double price, int inStock, int min, int max, ObservableList<Part> parts, String errorMessage) {
        double sumOfParts = 0.00;
        for (int i = 0; i < parts.size(); i++) {
            sumOfParts += parts.get(i).getPrice();
        }
        if (name.equals("")) {
            errorMessage += "A name has not been entered. ";
        }
        if (min < 0) {
            errorMessage += "The inventory level must be greater than 0. ";
        }
        if (price < 0.01) {
            errorMessage += "The price must be greater than $0. ";
        }
        if (min > max) {
            errorMessage += "The Min value must be less than the Max value. ";
        }
        if(inStock < min) {
            errorMessage += "Inventory level is lower than minimum value. ";
        }
        if(inStock > max) {
            errorMessage += "Inventory level is higher than the maximum value. ";
        }
        if (parts.size() < 1) {
            errorMessage += "Product must contain at least 1 part. ";
        }
        if (sumOfParts > price) {
            errorMessage += "Product price must be greater than cost of parts. ";
        }
        return errorMessage;
    }

    @Override
    public String toString() {
        return "P,"+this.productId+","+this.name+","+this.price+","+this.inStock+","+
                this.min+","+this.max;
    }
}
