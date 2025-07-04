import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();

        Product cheese = new Explorable("Cheese", 100, 10, tomorrow, 0.2);
        Product biscuits = new Explorable("Biscuits", 150, 5, tomorrow, 0.7);
        Product tv = new ShippableProduct("TV", 5000, 3, 10.0);
        Product scratchCard = new DigitalProduct("Scratch Card", 20, 50);

        Customer customer = new Customer("Ali", 10000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        cart.add(tv, 1);

        cart.checkout(customer);
    }
}