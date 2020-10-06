package com.api.model.requestbody;

import com.api.model.generalmodelinterface.HaveSignature;

public class RequestCreateTier implements HaveSignature {
    private int minimumTransaction;
    private int maximumTransaction;
    private float probability;
    private float discount;
    private String signature;

    public int getMinimumTransaction() {
        return minimumTransaction;
    }

    public void setMinimumTransaction(int minimumTransaction) {
        this.minimumTransaction = minimumTransaction;
    }

    public int getMaximumTransaction() {
        return maximumTransaction;
    }

    public void setMaximumTransaction(int maximumTransaction) {
        this.maximumTransaction = maximumTransaction;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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
        s.append(minimumTransaction).append(maximumTransaction).append(probability).append(discount);

        return s.toString();
    }
}
