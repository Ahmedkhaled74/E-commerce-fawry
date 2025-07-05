public class ShippableProduct extends Product implements Shippable{
    private double weight;
    private boolean expired;

    public ShippableProduct(String name, double price, int quantity, double weight, boolean expired) {
        super(name, price, quantity);
        this.weight = weight;
        this.expired = expired;
    }
    @Override
    public boolean isExpired() { return expired; }

    @Override
    public boolean isShippable() { return true; }

    @Override
    public double getWeight() { return weight; }
}

