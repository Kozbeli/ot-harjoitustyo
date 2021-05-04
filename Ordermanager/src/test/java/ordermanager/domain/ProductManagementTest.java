package ordermanager.domain;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ProductManagementTest {

    ProductManagement productManagement;

    @Before
    public void setUp() {
        try {
            this.productManagement = new ProductManagement("src/main/java/ordermanager/data/products.csv");
        } catch (IOException e) {
            System.out.println("IOException during constructing ProductManager");
        }
    }

    @Test
    public void productManagerCanBeConstructed() {
        assertTrue(this.productManagement != null);
    }

    @Test
    public void productManagementReturnsCorrectList() throws IOException {
        ObservableList<Product> testList = productManagement.getProducts();
        productManagement.products.clear();
        assertTrue(productManagement.products.isEmpty());
        productManagement.loadProductsList();
        for (int i = 0; i < testList.size(); i++) {
            assertTrue(productManagement.products.get(i).equals(testList.get(i)));
        }
    }
}
