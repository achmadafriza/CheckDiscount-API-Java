package com.api.model.requestbody;

import com.api.model.generalmodelinterface.HaveSignature;

public class RequestCreateOTP implements HaveSignature {
    private String username;
    private String email;
    private String signature;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String getAllParams() {
        StringBuilder s = new StringBuilder();
        s.append(username);
        s.append(email);

        return s.toString();
    }
}
