class Customer {
    String name;
    double balance;

    Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void deduct(double amount) {
        balance -= amount;
    }
}
