class DigitalProduct extends Product {
    DigitalProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    boolean isExpired() {
        return false;
    }

    @Override
    boolean requiresShipping() {
        return false;
    }
}
