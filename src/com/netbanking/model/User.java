package com.netbanking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String accountNumber;
    private String address;
    private String phoneNumber;
    private String mode;
    private String dateOfOpening;
    private String atmNumber;
    private String cardValidity;
    private Double balance;
    private String password;
    private List<Transaction> transactions;
    private List<Beneficiary> beneficiaries;
    private List<FixedDeposit> fds;

    public User(String name, String an, String ad, String ph, String mo, String dob, String atm, String val, double bal) {
        this.name = name; this.accountNumber = an; this.address = ad; this.phoneNumber = ph;
        this.mode = mo; this.dateOfOpening = dob; this.atmNumber = atm; this.cardValidity = val; this.balance = bal;
        this.password = "admin123";
        this.transactions = new ArrayList<>();
        this.beneficiaries = new ArrayList<>();
        this.fds = new ArrayList<>();
        // Add dummy initial transaction
        transactions.add(new Transaction("01-JAN-2020", "CREDIT", "Opening Balance", bal, bal));
        
        // Add dummy beneficiary
        beneficiaries.add(new Beneficiary("Hari", "123456789", "SBI", "SBI0001", 50000));
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
    public String getPassword() { return password; }
    public List<Transaction> getTransactions() { return transactions; }
    public List<Beneficiary> getBeneficiaries() { return beneficiaries; }
    public List<FixedDeposit> getFixedDeposits() { return fds; }
    
    // Setters
    public void setBalance(double balance) { this.balance = balance; }
    public void setPassword(String password) { this.password = password; }
    public void addTransaction(Transaction t) { this.transactions.add(t); }
    public void addBeneficiary(Beneficiary b) { this.beneficiaries.add(b); }
    public void addFixedDeposit(FixedDeposit fd) { this.fds.add(fd); }
}
