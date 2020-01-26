package com.netbanking.model;

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

    public User(String name, String an, String ad, String ph, String mo, String dob, String atm, String val, double bal) {
        this.name = name; this.accountNumber = an; this.address = ad; this.phoneNumber = ph;
        this.mode = mo; this.dateOfOpening = dob; this.atmNumber = atm; this.cardValidity = val; this.balance = bal;
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
    
    // Setters
    public void setBalance(double balance) { this.balance = balance; }
}
