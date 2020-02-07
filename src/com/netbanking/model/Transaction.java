package com.netbanking.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String date;
    private String type;
    private String description;
    private double amount;
    private double balanceAfter;

    public Transaction(String date, String type, String description, double amount, double balanceAfter) {
        this.date = date;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String getDate() { return date; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }
    
    public Object[] toRow() {
        return new Object[]{date, type, description, "Rs. " + amount, "Rs. " + balanceAfter};
    }
}
