package ordermanager.domain;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 * This class offers user interface a service for managing shopping cart and
 * confirmed orders.
 *
 * @author kozbeli
 */
public class OrderManagement {

    ObservableMap<Product, Integer> shoppingCart;
    User loggedInUser;
    private final String file;

    /**
     * This constructs the order management.
     *
     * @param file
     */
    public OrderManagement(String file) {
        shoppingCart = FXCollections.observableHashMap();
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
    public void addToCart(Product newProduct, int quantity) {
        quantity = this.shoppingCart.getOrDefault(newProduct, 0) + quantity;
        this.shoppingCart.put(newProduct, quantity);
    }

    /**
     * This method returns all the products currently in the shopping cart as an
     * ObservableMap.
     *
     * @return
     */
    public ObservableMap<Product, Integer> getCartSelections() {
        return this.shoppingCart;
    }

    /**
     * This method returns the content of the shopping cart as an
     * observableList.
     *
     * @return
     */
    public ObservableList<String> getCartView() {
        ObservableList<String> view = FXCollections.observableArrayList();
        for (Product p : shoppingCart.keySet()) {
            int quantity = shoppingCart.get(p);
            view.add(p.getName() + ", " + p.getPrice() + " x" + quantity);
        }
        return view;
    }

    public Double getTotal() {
        double total = 0;
        for (Product p : shoppingCart.keySet()) {
            total += p.getPrice() * shoppingCart.get(p);
        }
        return total;
    }

    /**
     * This method defines to whom the order will be addressed.
     *
     * @param user
     */
    public void setUser(User user) {
        this.loggedInUser = user;
    }

    /**
     * This method saves the content of the shopping cart into permanent
     * storage. It uses time stamp and username of the user currently logged in
     * to allocate the order.
     */
    public void saveOrder() {
        if (shoppingCart.isEmpty()) {
            return;
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(strDate + ";" + loggedInUser.getUsername() + ";");
            for (Product p : shoppingCart.keySet()) {
                String name = p.getName();
                int quantity = shoppingCart.get(p);
                writer.append(name + ";" + quantity + ";");
            }
            writer.append("\n");
        } catch (Exception e) {
            System.out.println("Unable to save order details");
        }
    }

    /**
     * This method returns every order, in which the username matches the user
     * currently logged in, as an observableList. When logged in as an admin,
     * this method returns all of the order history.
     *
     * @return ObservableList
     */
    public ObservableList<String> getOrders() {
        ObservableList<String> view = FXCollections.observableArrayList();
        try {
            Scanner reader = new Scanner(new File(this.file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                String username = loggedInUser.getUsername();
                if (username.equals(parts[1]) || username.equals("admin")) {
                    String str = parts[0] + " - " + parts[1] + "\n";
                    for (int i = 2; i < parts.length - 1; i += 2) {
                        String part = parts[i];
                        str = str + "\t" + parts[i] + " x" + parts[i + 1] + "\n";
                    }
                    view.add(str);
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to get Orders");
        }
        return view;
    }
}
