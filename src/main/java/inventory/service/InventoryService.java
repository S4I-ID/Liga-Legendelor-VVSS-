package inventory.service;

import inventory.model.InhousePart;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.ObservableList;

public class InventoryService {

    private InventoryRepository repo;

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    /**
     * ads inhouse part
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param partDynamicValue
     */
    public void addInhousePart(String name, double price, int inStock, int min, int max, int partDynamicValue) {
        if (partDynamicValue < 0) {
            return;
        }
        InhousePart inhousePart = new InhousePart(repo.getAutoPartId(), name, price, inStock, min, max, partDynamicValue);
        repo.addPart(inhousePart);
    }

    /**
     * adds aoutsourced part
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param partDynamicValue
     */
    public void addOutsourcePart(String name, double price, int inStock, int min, int max, String partDynamicValue) {
        OutsourcedPart outsourcedPart = new OutsourcedPart(repo.getAutoPartId(), name, price, inStock, min, max, partDynamicValue);
        repo.addPart(outsourcedPart);
    }

    /**
     * adds product
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param addParts
     */
    public void addProduct(String name, double price, int inStock, int min, int max, ObservableList<Part> addParts) {
        Product product = new Product(repo.getAutoProductId(), name, price, inStock, min, max, addParts);
        repo.addProduct(product);
    }

    /**
     * gets all parts
     * @return
     */
    public ObservableList<Part> getAllParts() {
        return repo.getAllParts();
    }

    /**
     * gets all products
     * @return
     */
    public ObservableList<Product> getAllProducts() {
        return repo.getAllProducts();
    }


    /**
     * search for part
     * @param search
     * @return
     */
    public Part lookupPart(String search) {
        return repo.lookupPart(search);
    }

    /**
     * search for product
     * @param search
     * @return
     */
    public Product lookupProduct(String search) {
        return repo.lookupProduct(search);
    }

    /**
     * update inhouse part
     * @param partIndex
     * @param partId
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param partDynamicValue
     */
    public void updateInhousePart(int partIndex, int partId, String name, double price, int inStock, int min, int max, int partDynamicValue) {
        if (partDynamicValue < 0) {
            return;
        }
        InhousePart inhousePart = new InhousePart(partId, name, price, inStock, min, max, partDynamicValue);
        repo.updatePart(partIndex, inhousePart);
    }

    /**
     * update outsourced part
     * @param partIndex
     * @param partId
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param partDynamicValue
     */
    public void updateOutsourcedPart(int partIndex, int partId, String name, double price, int inStock, int min, int max, String partDynamicValue) {
        OutsourcedPart outsourcedPart = new OutsourcedPart(partId, name, price, inStock, min, max, partDynamicValue);
        repo.updatePart(partIndex, outsourcedPart);
    }

    /**
     * update product
     * @param productIndex
     * @param productId
     * @param name
     * @param price
     * @param inStock
     * @param min
     * @param max
     * @param addParts
     */
    public void updateProduct(int productIndex, int productId, String name, double price, int inStock, int min, int max, ObservableList<Part> addParts) {
        Product product = new Product(productId, name, price, inStock, min, max, addParts);
        repo.updateProduct(productIndex, product);
    }

    /**
     * deletes part
     * @param part
     */
    public void deletePart(Part part) {
        repo.deletePart(part);
    }

    /**
     * deletes product
     * @param product
     */
    public void deleteProduct(Product product) {
        repo.deleteProduct(product);
    }

}