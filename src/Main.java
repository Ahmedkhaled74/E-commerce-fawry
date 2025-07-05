// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Product cheese = new ShippableProduct("Cheese", 100, 5, 0.2, false);
        Product biscuits = new ShippableProduct("Biscuits", 150, 3, 0.7, false);
        Product tv = new ShippableProduct("TV", 3000, 2, 8.0, false);
        Product scratchCard = new NonShippableProduct("ScratchCard", 50, 10, false);

        // Define customer
        Customer customer = new Customer("Ahmed", 1000);

        // Add to cart
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        // Perform checkout
        CheckoutService.checkout(customer, cart);
    }
}