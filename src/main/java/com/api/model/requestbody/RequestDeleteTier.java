package com.api.model.requestbody;

import com.api.model.generalmodelinterface.HaveSignature;

public class RequestDeleteTier implements HaveSignature {
    private int id;
    private String signature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id + "";
    }
}
