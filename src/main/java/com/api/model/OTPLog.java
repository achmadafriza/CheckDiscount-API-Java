package com.api.model;

import com.api.model.generalmodelinterface.HaveID;

public class OTPLog implements HaveID {
    private int otpID;
    private int customerID;
    private String username;
    private String otpNumber;
    private String email;
    private long createdAt;
    private long validUntil;
    private String status;

    public int getID() {
        return otpID;
    }

    public void setID(int otpID) {
        this.otpID = otpID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(String otpNumber) {
        this.otpNumber = otpNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(long validUntil) {
        this.validUntil = validUntil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static int isVoid() {
        return 0;
    }

    public static int isPending() {
        return 1;
    }

    public static int isOK() {
        return 2;
    }
}
