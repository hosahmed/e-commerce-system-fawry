import java.util.Date;

class Explorable extends Product implements ShippableInterface {
    Date expiryDate;
    double weight;

    Explorable(String name, double price, int quantity, Date expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    boolean isExpired() {
        return new Date().after(expiryDate);
    }

    @Override
    boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }
}
