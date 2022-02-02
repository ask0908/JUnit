package main;

public class Franc extends Money {

    private String currency;

    public Franc(int amount, String currency) {
        super(amount, currency);
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String currency() {
        return currency;
    }

}
