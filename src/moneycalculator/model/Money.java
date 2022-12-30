package moneycalculator.model;

public class Money {
    private final Currency currency;
    private final Double amount;
    
    public Money (Currency currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getAmount() {
        return amount;
    }
}