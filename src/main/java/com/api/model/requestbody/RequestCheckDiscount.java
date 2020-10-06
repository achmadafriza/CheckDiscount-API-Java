package com.api.model.requestbody;

import com.api.model.generalmodelinterface.HaveSignature;

public class RequestCheckDiscount implements HaveSignature {
    private String username;
    private int ammount;
    private long time;
    private String signature;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String getAllParams() {
        StringBuilder s = new StringBuilder();
        s.append(username);
        s.append(ammount);
        s.append(time);

        return s.toString();
    }
}
