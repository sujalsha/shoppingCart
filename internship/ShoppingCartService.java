package internship;
import java.sql.*;
import java.util.List;

public class ShoppingCartService {
    private ShoppingCart cart;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ShoppingCartService() {
        this.cart = new ShoppingCart();
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Establish the database connection
            String url = "jdbc:mysql://localhost:3306/internship";
            String username = "root";
            String password = "sujalcode";
    
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Add product to the cart and update the database
    public void addProductToCart(Product product) {
        cart.addProduct(product);
        updateDatabase(product, "add");
    }

    // Remove product from the cart and update the database
    public void removeProductFromCart(Product product) {
        cart.removeProduct(product);
        updateDatabase(product, "remove");
    }

    // Sign out items from the cart and update the database
    public void signOutCart() {
        cart.signOut();
        updateDatabase(null, "signout");
    }

    // Get items in the cart
    public List<Product> getCartItems() {
        return cart.getItems();
    }

    // Update the database with the changes made to the cart
    // Update the database with the changes made to the cart
private void updateDatabase(Product product, String operation) {
    try {
        String query;
        if (operation.equals("add")) {
            query = "INSERT INTO cart_items (product_id, product_name, product_price) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
        } else if (operation.equals("remove")) {
            query = "DELETE FROM cart_items WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductId());
        } else if (operation.equals("signout")) {
            query = "TRUNCATE TABLE cart_items"; // Clear the cart_items table
            preparedStatement = connection.prepareStatement(query);
        }
        
        // Execute the SQL statement
        preparedStatement.executeUpdate();
        
        // Commit the transaction
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        // Rollback the transaction if an exception occurs
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    }
    
}

}
