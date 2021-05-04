package ordermanager.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class offers user interface a service for managing shopping cart and
 * confirmed orders.
 *
 * @author kozbeli
 */
public class OrderManagement {

    ObservableList<Product> shoppingCart;
    private final String file;

    /**
     * This constructs the order management.
     *
     * @param file
     */
    public OrderManagement(String file) {
        shoppingCart = FXCollections.observableArrayList();
        this.file = file;
    }

    /**
     * This method clears the shopping cart.
     */
    public void newCart() {
        this.shoppingCart.clear();
    }

    /**
     * This method adds a new product into shopping cart.
     *
     * @param newProduct
     */
    public void addToCart(Product newProduct) {
        this.shoppingCart.add(newProduct);
    }

    /**
     * This method returns all the products currently in the shopping cart as an
     * ObservableList.
     *
     * @return
     */
    public ObservableList<Product> getCartSelections() {
        return this.shoppingCart;
    }

}
