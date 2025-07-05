import java.util.ArrayList;

public class Cart {
    ArrayList<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0 || quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Invalid quantity for product: " + product.getName());
        }
        items.add(new CartItem(product, quantity));
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
