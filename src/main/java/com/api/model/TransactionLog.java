package com.api.model;

import com.api.model.generalmodelinterface.HaveID;

public class TransactionLog implements HaveID {
    private int transactionID;
    private int customerID;
    private int tierID;
    private String username;
    private boolean discounted;
    private int transactionAmmount;
    private int discountedAmmount;
    private long transactionTime;

    public int getID() {
        return transactionID;
    }

    public void setID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTierID() {
        return tierID;
    }

    public void setTierID(int tierID) {
        this.tierID = tierID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTransactionAmmount() {
        return transactionAmmount;
    }

    public void setTransactionAmmount(int transactionAmmount) {
        this.transactionAmmount = transactionAmmount;
    }

    public int getDiscountedAmmount() {
        return discountedAmmount;
    }

    public void setDiscountedAmmount(int discountedAmmount) {
        this.discountedAmmount = discountedAmmount;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }
}
