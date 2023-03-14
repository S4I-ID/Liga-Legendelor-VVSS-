
package inventory.model;


/**
 * Clasa care descrie o piesa produsa de o companie externa
 */
public class OutsourcedPart extends Part {
    
    // Declare fields
    private String companyName;

    // Constructor
    public OutsourcedPart(int partId, String name, double price, int inStock, int min, int max, String companyName) {
        super(partId, name, price, inStock, min, max);
        this.companyName = companyName;
    }
    
    // Getter

    /**
     * Gets company name
     * @return String
     */
    public String getCompanyName() {
        return companyName;
    }
    
    // Setter

    /**
     * Sets company name
     * @param companyName String
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "O,"+super.toString()+","+getCompanyName();
    }

}
