
package com.api.model;

import com.api.model.generalmodelinterface.HaveID;
import com.api.model.requestbody.RequestUpdateTier;

public class TransactionTier implements HaveID {
    private int tierID;
    private int minimumTransaction;
    private int maximumTransaction;
    private float probability;
    private float discount;

    public TransactionTier() {}

    public TransactionTier(RequestUpdateTier tier) {
        tierID = tier.getId();
        minimumTransaction = tier.getMinimumTransaction();
        maximumTransaction = tier.getMaximumTransaction();
        probability = tier.getProbability();
        discount = tier.getDiscount();
    }

    public int getID() {
        return tierID;
    }

    public void setID(int tierID) {
        this.tierID = tierID;
    }

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
}
