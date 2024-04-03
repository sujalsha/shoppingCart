package internship;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a shopping cart service
        ShoppingCartService cartService = new ShoppingCartService();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add product to cart");
            System.out.println("2. Remove product from cart");
            System.out.println("3. Sign out cart");
            System.out.println("4. Display cart items");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter product ID:");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.println("Enter product name:");
                    String productName = scanner.nextLine();
                    System.out.println("Enter product price:");
                    double productPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character

                    Product newProduct = new Product(productId, productName, productPrice);
                    cartService.addProductToCart(newProduct);
                    System.out.println("Product added to cart successfully!");
                    break;
                case 2:
                    System.out.println("Enter product ID to remove:");
                    int removeProductId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Product productToRemove = null;
                    List<Product> cartItems = cartService.getCartItems();
                    for (Product item : cartItems) {
                        if (item.getProductId() == removeProductId) {
                            productToRemove = item;
                            break;
                        }
                    }
                    if (productToRemove != null) {
                        cartService.removeProductFromCart(productToRemove);
                        System.out.println("Product removed from cart successfully!");
                    } else {
                        System.out.println("Product not found in cart!");
                    }
                    break;
                case 3:
                    cartService.signOutCart();
                    System.out.println("Cart signed out successfully!");
                    break;
                case 4:
                    cartItems = cartService.getCartItems();
                    if (cartItems.isEmpty()) {
                        System.out.println("Cart is empty");
                    } else {
                        System.out.println("Items in the cart:");
                        for (Product item : cartItems) {
                            System.out.println(item.getName() + " - $" + item.getPrice());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }
}
