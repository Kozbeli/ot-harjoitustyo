package ordermanager.domain;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductManagement {

    public ObservableList<Product> products;
    private final String file;

    public ProductManagement(String filePath) throws IOException {
        products = FXCollections.observableArrayList();
        this.file = filePath;
        loadProductsList();
    }

    private void loadProductsList() throws IOException {
        Scanner reader = new Scanner(new File(this.file));
        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(";");
            Product product = new Product(parts[0], Double.valueOf(parts[1]));
            products.add(product);
        }
    }

    public ObservableList<Product> getProducts() {
        return this.products;
    }
}
