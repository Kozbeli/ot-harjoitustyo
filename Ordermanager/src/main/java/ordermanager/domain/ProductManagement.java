package ordermanager.domain;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class offers a service to user interface for managing the products.
 *
 * @author kozbeli
 */
public class ProductManagement {

    public ObservableList<Product> products;
    private final String file;

    /**
     * This constructs the product management.
     *
     * @param filePath
     * @throws IOException
     */
    public ProductManagement(String filePath) throws IOException {
        products = FXCollections.observableArrayList();
        this.file = filePath;
        loadProductsList();
    }

    /**
     * This method loads the product list from permanent storage into
     * ObservableList.
     *
     * @throws IOException
     */
    public void loadProductsList() throws IOException {
        Scanner reader = new Scanner(new File(this.file));
        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(";");
            Product product = new Product(parts[0], Double.valueOf(parts[1]));
            products.add(product);
        }
    }

    /**
     * This method returns products as Observable list to user interface.
     *
     * @return products in a form of ObservableList.
     */
    public ObservableList<Product> getProducts() {
        return this.products;
    }
}
