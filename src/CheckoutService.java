import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new RuntimeException("Cart is empty");

        double subtotal = 0;
        double shippingFee = 0;
        ArrayList<Shippable> shippables = new ArrayList<>();
        Map<String, Integer> shippableCounts = new HashMap<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (item.getQuantity() > product.getQuantity()) {
                throw new RuntimeException(product.getName() + " is out of stock");
            }

            if (product.isExpired()) {
                throw new RuntimeException(product.getName() + " is expired");
            }
            subtotal += item.getTotalPrice();

            if (item.isShippable()) {
                Shippable s = (Shippable) product;
                shippables.add(s);
                shippableCounts.put(s.getName(), shippableCounts.getOrDefault(s.getName(), 0) + item.getQuantity());
                shippingFee += 15; // Flat shipping fee per shippable item type
            }
        }

        double total = subtotal + shippingFee;

        if (!customer.deduct(total)) {
            throw new RuntimeException("Insufficient balance");
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        if (!shippables.isEmpty()) {
            ShippingService.ship(shippables, shippableCounts);
        }

        // Checkout receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %.0f\n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shippingFee);
        System.out.printf("Amount           %.0f\n", total);
        System.out.printf("Remaining Balance %.0f\n", customer.getBalance());
    }
}
