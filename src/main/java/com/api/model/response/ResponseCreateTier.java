package com.api.model.response;

import com.api.model.requestbody.RequestCreateTier;
import org.springframework.http.HttpStatus;

public class ResponseCreateTier {
    private HttpStatus status;
    private int minimumTransaction;
    private int maximumTransaction;
    private float probability;
    private float discount;

    public ResponseCreateTier(HttpStatus status, RequestCreateTier request) {
        setStatus(status);
        setMinimumTransaction(request.getMinimumTransaction());
        setMaximumTransaction(request.getMaximumTransaction());
        setProbability(request.getProbability());
        setDiscount(request.getDiscount());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
