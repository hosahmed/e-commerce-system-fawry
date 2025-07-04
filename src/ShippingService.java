import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ShippingService {
    static void ship(List<Shippable> items) {
        System.out.println("Shipment");
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> weights = new HashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = item.getName();
            counts.put(name, counts.getOrDefault(name, 0) + 1);
            weights.put(name, item.getWeight());
            totalWeight += item.getWeight();
        }

        for (String name : counts.keySet()) {
            int count = counts.get(name);
            double weight = weights.get(name) * count * 1000;
            System.out.println(count + "x " + name + "\t" + weight + "g");
        }

        System.out.printf("Total weight: %.0fkg%n", totalWeight);
    }
}
