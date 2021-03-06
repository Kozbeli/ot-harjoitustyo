package ordermanager.domain;

import java.io.IOException;
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
}
