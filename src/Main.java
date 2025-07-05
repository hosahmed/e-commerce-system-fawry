import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1); // tomorrow
        Date futureDate = cal.getTime();
        cal.add(Calendar.DATE, -3); // 2 days ago
        Date pastDate = cal.getTime();

        Product cheese = new Expirable("Cheese", 100, 5, futureDate, 0.2);
        Product biscuits = new Expirable("Biscuits", 150, 3, futureDate, 0.7);
        Product expiredMilk = new Expirable("Milk", 60, 2, pastDate, 0.5); // expired
        Product tv = new ShippableProduct("TV", 5000, 2, 10);
        Product card = new DigitalProduct("Scratch Card", 20, 100);

        Customer customer = new Customer("Hossam", 10000);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(card, 3);

        try {
            cart.add(biscuits, 10); // stock is only 2 left
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Cart emptyCart = new Cart();
        try {
            emptyCart.checkout(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Cart expiredCart = new Cart();
        expiredCart.add(expiredMilk, 1);
        try {
            expiredCart.checkout(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Customer pCustomer = new Customer("LowBalance", 10);
        Cart bigCart = new Cart();
        bigCart.add(tv, 1);
        try {
            bigCart.checkout(pCustomer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Cart testCart = new Cart();
        testCart.add(cheese, 3);
        testCart.add(tv, 1);
        testCart.remove(cheese, 1);
        testCart.remove(cheese, 2);
        testCart.remove(tv, 1);


        Product notInCart = new ShippableProduct("Nonexistent", 99, 1, 1.0);
        testCart.remove(notInCart, 1);
        testCart.remove(card, 0);
        testCart.remove(card, -1);

        Cart shipCart = new Cart();
        Customer Shipper = new Customer("Shipper", 1000);
        shipCart.add(cheese, 1);
        shipCart.add(biscuits, 2);
        shipCart.checkout(Shipper);
    }
}