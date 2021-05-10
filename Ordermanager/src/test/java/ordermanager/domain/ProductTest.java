package ordermanager.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    Product product;

    @Before
    public void setUp() {
        this.product = new Product("testProduct", 1.23);
    }

    @Test
    public void productCanBeCreated() {
        assertTrue(this.product != null);
    }

    @Test
    public void productInfoIsCorrect() {
        assertEquals("testProduct", this.product.getName());
        assertTrue(1.23 == this.product.getPrice());
        assertEquals("testProduct - 1.23", this.product.toString());
    }

}
