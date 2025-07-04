class ShippableProduct extends Product implements Shippable {
    double weight;

    ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    boolean isExpired() {
        return false;
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
