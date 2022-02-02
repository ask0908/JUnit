package main;

public class Dollar extends Money {

    private String currency;

    public Dollar (int amount, String currency) {
        super(amount, currency);
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String currency() {
        return currency;
    }
}