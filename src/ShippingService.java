import java.util.ArrayList;
import java.util.Map;

public class ShippingService {
    public static void ship(ArrayList<Shippable> items, Map<String, Integer> counts) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        for (Shippable item : items) {
            int count = counts.get(item.getName());
            double weight = item.getWeight() * count;
            totalWeight += weight;
            System.out.printf("%dx %-12s %.0fg\n", count, item.getName(), weight * 1000);
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
        System.out.println();
    }
}
