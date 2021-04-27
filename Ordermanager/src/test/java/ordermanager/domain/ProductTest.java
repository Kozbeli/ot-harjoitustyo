package ordermanager.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {
    
    Product product;
    
    public ProductTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.product = new Product("testProduct", 1.23);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void productCanBeCreated() {
        assertTrue(this.product != null);
    }
    
    @Test
    public void productInfoIsCorrect() {
        assertEquals("testProduct", this.product.getName());
        assertTrue(1.23 == this.product.getPrice());
        assertEquals("testProduct;1.23", this.product.toString());
    }

}
