import java.util.ArrayList;
import java.util.List;

class Cart {
    List<CartItem> items = new ArrayList<>();

    void add(Product product, int quantity) {
        if (product.quantity < quantity) throw new RuntimeException("Low stock");
        items.add(new CartItem(product, quantity));
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) subtotal += item.getTotalPrice();
        return subtotal;
    }

    List<ShippableInterface> getShippableItems() {
        List<ShippableInterface> shippableInterfaces = new ArrayList<>();
        for (CartItem item : items) {
            if (item.product.requiresShipping()) {
                for (int i = 0; i < item.quantity; i++) {
                    shippableInterfaces.add((ShippableInterface) item.product);
                }
            }
        }
        return shippableInterfaces;
    }

    boolean hasExpiredItems() {
        for (CartItem item : items) if (item.product.isExpired()) return true;
        return false;
    }

    boolean hasOutOfStockItems() {
        for (CartItem item : items) if (item.quantity > item.product.quantity) return true;
        return false;
    }

    void checkout(Customer customer) {
        if (isEmpty()) throw new RuntimeException("Empty cart");
        if (hasExpiredItems()) throw new RuntimeException("Expired item");
        if (hasOutOfStockItems()) throw new RuntimeException("Out of stock");

        double subtotal = getSubtotal();
        double shippingFee = getShippableItems().isEmpty() ? 0 : 30;
        double total = subtotal + shippingFee;

        if (customer.balance < total) throw new RuntimeException("Low balance");

        for (CartItem item : items) item.product.quantity -= item.quantity;

        List<ShippableInterface> shippingItems = getShippableItems();
        if (!shippingItems.isEmpty()) ShippingService.ship(shippingItems);

        customer.deduct(total);

        System.out.println("\nReceipt");
        for (CartItem item : items)
            System.out.println(item.quantity + "x " + item.product.name + "\t" + item.getTotalPrice());
        System.out.println("-------------");
        System.out.println("Subtotal\t" + subtotal);
        System.out.println("Shipping\t" + shippingFee);
        System.out.println("Total\t\t" + total);
        System.out.println("Balance\t\t" + customer.balance);
    }
}
