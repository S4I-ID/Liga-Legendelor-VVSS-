package inventory.repository;


import inventory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Clasa de repository pentru inventar, mentine fisierele cu date
 */
public class InventoryRepository {

    private static String filename = "data/items.txt";
    private Inventory inventory;

    public InventoryRepository() {
        this.inventory = new Inventory();
        readParts();
        readProducts();
    }

    /**
     * reads parts from file
     */
    public void readParts() {
        //ClassLoader classLoader = InventoryRepository.class.getClassLoader();
        File file = new File(filename);
        ObservableList<Part> listP = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                Part part = getPartFromString(line);
                if (part != null)
                    listP.add(part);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventory.setAllParts(listP);
    }

    /**
     * searches part from string
     * @param line String
     * @return Part
     */
    private Part getPartFromString(String line) {
        Part item = null;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        String type = st.nextToken();
        if (type.equals("I")) {
            int id = Integer.parseInt(st.nextToken());
            inventory.setAutoPartId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            int idMachine = Integer.parseInt(st.nextToken());
            item = new InhousePart(id, name, price, inStock, minStock, maxStock, idMachine);
        }
        if (type.equals("O")) {
            int id = Integer.parseInt(st.nextToken());
            inventory.setAutoPartId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            String company = st.nextToken();
            item = new OutsourcedPart(id, name, price, inStock, minStock, maxStock, company);
        }
        return item;
    }

    /**
     * reads products from file
     */
    public void readProducts() {
        //ClassLoader classLoader = InventoryRepository.class.getClassLoader();
        File file = new File(filename);

        ObservableList<Product> listP = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                Product product = getProductFromString(line);
                if (product != null)
                    listP.add(product);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventory.setProducts(listP);
    }

    /**
     * Gets product from string
     * @param line String
     * @return Product
     */
    private Product getProductFromString(String line) {
        Product product = null;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        String type = st.nextToken();
        if (type.equals("P")) {
            int id = Integer.parseInt(st.nextToken());
            inventory.setAutoProductId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            String partIDs = st.nextToken();

            StringTokenizer ids = new StringTokenizer(partIDs, ":");
            ObservableList<Part> list = FXCollections.observableArrayList();
            while (ids.hasMoreTokens()) {
                String idP = ids.nextToken();
                Part part = inventory.lookupPart(idP);
                if (part != null)
                    list.add(part);
            }
            product = new Product(id, name, price, inStock, minStock, maxStock, list);
            product.setAssociatedParts(list);
        }
        return product;
    }

    /**
     * writes all to file
     */
    public void writeAll() {

        //ClassLoader classLoader = InventoryRepository.class.getClassLoader();
        File file = new File(filename);

        ObservableList<Part> parts = inventory.getAllParts();
        ObservableList<Product> products = inventory.getProducts();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Part p : parts) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }

            for (Product pr : products) {
                String line = pr.toString() + ",";
                ObservableList<Part> list = pr.getAssociatedParts();
                int index = 0;
                while (index < list.size() - 1) {
                    line = line + list.get(index).getPartId() + ":";
                    index++;
                }
                if (index == list.size() - 1)
                    line = line + list.get(index).getPartId();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds part
     * @param part Part
     */
    public void addPart(Part part) {
        inventory.addPart(part);
        writeAll();
    }

    /**
     * add product
     * @param product Product
     */
    public void addProduct(Product product) {
        inventory.addProduct(product);
        writeAll();
    }

    /**
     * get part id
     * @return int
     */
    public int getAutoPartId() {
        return inventory.getAutoPartId();
    }

    /**
     * get product id
     */
    public int getAutoProductId() {
        return inventory.getAutoProductId();
    }

    /**
     * get all parts
     * @return ObservableList(Part)
     */
    public ObservableList<Part> getAllParts() {
        return inventory.getAllParts();
    }

    /**
     * Gets all products
     * @return ObservableList(Product)
     */
    public ObservableList<Product> getAllProducts() {
        return inventory.getProducts();
    }

    /**
     * Search part
     * @param search String
     * @return Part
     */
    public Part lookupPart(String search) {
        return inventory.lookupPart(search);
    }

    /**
     * search product
     * @param search String
     * @return Product
     */
    public Product lookupProduct(String search) {
        return inventory.lookupProduct(search);
    }

    /**
     * update part
     * @param partIndex int
     * @param part Part
     */
    public void updatePart(int partIndex, Part part) {
        inventory.updatePart(partIndex, part);
        writeAll();
    }

    /**
     * update product
     * @param productIndex int
     * @param product Product
     */
    public void updateProduct(int productIndex, Product product) {
        inventory.updateProduct(productIndex, product);
        writeAll();
    }

    /**
     * delete part
     * @param part Part
     */
    public void deletePart(Part part) {
        for (Product p : inventory.getProducts()) {
            if (p.getAssociatedParts().contains(part)) {
                return;
            }
        }
        inventory.deletePart(part);
        writeAll();
    }

    /**
     * Delete product
     * @param product Product
     */
    public void deleteProduct(Product product) {
        inventory.removeProduct(product);
        writeAll();
    }

    /**
     * Gets inventory
     * @return Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets inventory
     * @param inventory Inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
