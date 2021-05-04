package ordermanager.domain;

public class Product {

    private String name;
    private double price;

    /**
     * This constructs a product.
     *
     * @param name
     * @param price
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * This method returns the name of the product.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the price of the product.
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method returns all the details of the product.
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name + ";" + this.price;
    }
}
