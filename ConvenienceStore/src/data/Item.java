
package data;

import java.io.Serializable;

public class Item implements Serializable{
    private Product product;
    private int quantity;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("product=%s, quantity=%d, price=%.3f\n", product.getName(), quantity, product.getPrice());
    }
}
