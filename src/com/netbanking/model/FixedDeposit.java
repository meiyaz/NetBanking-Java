package com.netbanking.model;

import java.io.Serializable;

public class FixedDeposit implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private double principal;
    private int durationMonths;
    private double interestRate;
    private double maturityAmount;
    private String startDate;

    public FixedDeposit(String id, double principal, int durationMonths, double interestRate, double maturityAmount, String startDate) {
        this.id = id;
        this.principal = principal;
        this.durationMonths = durationMonths;
        this.interestRate = interestRate;
        this.maturityAmount = maturityAmount;
        this.startDate = startDate;
    }

    public String getId() { return id; }
    public double getPrincipal() { return principal; }
    public int getDurationMonths() { return durationMonths; }
    public double getInterestRate() { return interestRate; }
    public double getMaturityAmount() { return maturityAmount; }
    public String getStartDate() { return startDate; }

    public Object[] toRow() {
        return new Object[]{id, "Rs. " + principal, durationMonths + " months", interestRate + "%", "Rs. " + maturityAmount, startDate};
    }
}
