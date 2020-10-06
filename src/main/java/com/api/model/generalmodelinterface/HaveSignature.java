package com.api.model.generalmodelinterface;

public interface HaveSignature {
    String key = "12345";

    public String getSignature();
    public void setSignature(String signature);
    public String getAllParams();

    default public String getKey() {
        return key;
    }
}
