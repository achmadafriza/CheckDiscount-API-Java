package com.api.model.requestbody;

import com.api.model.generalmodelinterface.HaveSignature;

public class RequestCheckOTP implements HaveSignature {
    private String otpNumber;
    private String username;
    private String signature;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setOtpNumber(String otpNumber) {
        this.otpNumber = otpNumber;
    }

    public String getOtpNumber() {
        return otpNumber;
    }

    @Override
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public String getAllParams() {
        StringBuilder s = new StringBuilder();
        s.append(otpNumber).append(username);

        return s.toString();
    }
}
