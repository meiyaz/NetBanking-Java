package com.netbanking.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String accountNumber;
    private String address;
    private String phoneNumber;
    private String mode;
    private String dateOfOpening;
    private String atmNumber;
    private String cardValidity;
    private double balance;
    private List<Transaction> transactions;

    public User(String name, String an, String ad, String ph, String mo, String dob, String atm, String val, double bal) {
        this.name = name; this.accountNumber = an; this.address = ad; this.phoneNumber = ph;
        this.mode = mo; this.dateOfOpening = dob; this.atmNumber = atm; this.cardValidity = val; this.balance = bal;
        this.transactions = new ArrayList<>();
        // Add dummy initial transaction
        transactions.add(new Transaction("01-JAN-2020", "CREDIT", "Opening Balance", bal, bal));
    }

    // Getters
    public String getName() { return name; }
    public String getAccountNumber() { return accountNumber; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getMode() { return mode; }
    public String getDateOfOpening() { return dateOfOpening; }
    public String getAtmNumber() { return atmNumber; }
    public String getCardValidity() { return cardValidity; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }
    
    // Setters
    public void setBalance(double balance) { this.balance = balance; }
    public void addTransaction(Transaction t) { this.transactions.add(t); }
}
