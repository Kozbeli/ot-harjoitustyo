package ordermanager.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderManagementTest {

    String filePath;
    OrderManagement orderManagement;

    @Before
    public void setUp() {
        filePath = "src/test/java/ordermanager/data/fakeOrders.csv";
        initializeFakeOrders();
        orderManagement = new OrderManagement(filePath);

    }

    @Test
    public void orderManagementCanBeConstructed() {
        assertTrue(orderManagement != null);
    }

    @Test
    public void productsCanBeAddedToShoppingCart() {
        ObservableMap<Product, Integer> map = orderManagement.getCartSelections();
        assertTrue(map.keySet().isEmpty());
        Product newProduct = new Product("test", 11.22);
        orderManagement.addToCart(newProduct, 1);
        assertTrue(map.get(newProduct) == 1);
        orderManagement.addToCart(newProduct, 2);
        assertTrue(map.get(newProduct) == 3);
        orderManagement.newCart();
        map = orderManagement.getCartSelections();
        assertTrue(map.keySet().isEmpty());
    }

    @Test
    public void getCartViewReturnsCorrectView() {
        Product test1 = new Product("test1", 10.0);
        Product test2 = new Product("test2", 20.0);
        Product test3 = new Product("test3", 30.0);
        orderManagement.addToCart(test1, 1);
        orderManagement.addToCart(test2, 1);
        orderManagement.addToCart(test3, 1);
        ObservableList<String> list = orderManagement.getCartView();
        assertTrue(list.contains("test1, 10.0 x1"));
        assertTrue(list.contains("test2, 20.0 x1"));
        assertTrue(list.contains("test3, 30.0 x1"));
        orderManagement.newCart();
    }

    @Test
    public void shoppingCartTotalSumIsCorrect() {
        orderManagement.newCart();
        Product test4 = new Product("test4", 10.0);
        orderManagement.addToCart(test4, 1);
        assertTrue(orderManagement.getTotal() == 10.0);
        orderManagement.addToCart(test4, 2);
        assertTrue(orderManagement.getTotal() == 30.0);
        Product test5 = new Product("test5", 20.0);
        orderManagement.addToCart(test5, 1);
        assertTrue(orderManagement.getTotal() == 50.0);
        orderManagement.newCart();
    }

    @Test
    public void ordersCanBeSaved() {
        orderManagement.newCart();
        User testDummie1 = new User("test1", "test1", "test1", "test1", "test1", 123, "test1", "test1");
        orderManagement.setUser(testDummie1);
        ObservableList<String> list = orderManagement.getOrders();
        assertTrue(list.isEmpty());
        Product test6 = new Product("test6", 20.0);
        Product test7 = new Product("test7", 30.0);
        orderManagement.addToCart(test6, 1);
        orderManagement.addToCart(test7, 2);
        orderManagement.saveOrder();
        list = orderManagement.getOrders();
        assertTrue(list.get(0).contains(testDummie1.getUsername()));
        assertTrue(list.get(0).contains(test6.getName() + " x1"));
        assertTrue(list.get(0).contains(test7.getName() + " x2"));
    }

    @Test
    public void adminCanViewAllTheOrders() {
        initializeFakeOrders();
        orderManagement.newCart();
        orderManagement.saveOrder();
        ObservableList<String> list = orderManagement.getOrders();
        assertTrue(list.isEmpty());

        User testDummie2 = new User("test2", "test2", "test2", "test2", "test2", 123, "test2", "test2");
        orderManagement.setUser(testDummie2);
        Product test8 = new Product("test8", 40.0);
        Product test9 = new Product("test9", 50.0);
        orderManagement.addToCart(test8, 2);
        orderManagement.addToCart(test9, 2);
        orderManagement.saveOrder();
        list = orderManagement.getOrders();
        assertTrue(list.get(0).contains(test8.getName() + " x2"));
        assertTrue(list.get(0).contains(test9.getName() + " x2"));

        User testDummie3 = new User("test3", "test3", "test3", "test3", "test3", 123, "test3", "test3");
        orderManagement.setUser(testDummie3);
        list = orderManagement.getOrders();
        assertTrue(list.isEmpty());

        orderManagement.newCart();
        Product test10 = new Product("test10", 100.0);
        Product test11 = new Product("test11", 101.0);
        orderManagement.addToCart(test10, 1);
        orderManagement.addToCart(test11, 1);
        orderManagement.saveOrder();
        list = orderManagement.getOrders();
        assertTrue(list.get(0).contains(test10.getName() + " x1"));
        assertTrue(list.get(0).contains(test11.getName() + " x1"));

        User admin = new User("admin", "admin", "admin", "admin", "admin", 123, "admin", "admin");
        orderManagement.setUser(admin);
        list = orderManagement.getOrders();
        assertTrue(list.get(0).contains(testDummie2.getName()));
        assertTrue(list.get(0).contains(test8.getName()));
        assertTrue(list.get(0).contains(test9.getName()));
        assertTrue(list.get(1).contains(testDummie3.getName()));
        assertTrue(list.get(1).contains(test10.getName()));
        assertTrue(list.get(1).contains(test11.getName()));
    }

    private void initializeFakeOrders() {
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not initialize fake orders");
        }
    }
}
