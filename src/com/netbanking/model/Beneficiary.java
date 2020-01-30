package com.netbanking.model;

public class Beneficiary {
    private String name;
    private String accountNumber;
    private String bankName;
    private String ifscCode;
    private double transferLimit;

    public Beneficiary(String name, String accountNumber, String bankName, String ifscCode, double transferLimit) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.transferLimit = transferLimit;
    }

    public String getName() { return name; }
    public String getAccountNumber() { return accountNumber; }
    public String getBankName() { return bankName; }
    public String getIfscCode() { return ifscCode; }
    public double getTransferLimit() { return transferLimit; }
    
    public Object[] toRow() {
        return new Object[]{name, accountNumber, bankName, ifscCode, "Rs. " + transferLimit};
    }
}
