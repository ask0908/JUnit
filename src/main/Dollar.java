package main;

public class Dollar extends Money {

    // IDE는 final을 붙이는 걸 권장함
    private final String currency;

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