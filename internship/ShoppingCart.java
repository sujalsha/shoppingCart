package internship;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Add a product to the cart
    public void addProduct(Product product) {
        items.add(product);
    }

    // Remove a product from the cart
    public void removeProduct(Product product) {
        items.remove(product);
    }

    // Sign out the items from the cart
    public void signOut() {
        items.clear();
    }

    // Get the items in the cart
    public List<Product> getItems() {
        return items;
    }
}
