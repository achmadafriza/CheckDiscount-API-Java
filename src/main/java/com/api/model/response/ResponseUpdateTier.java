package com.api.model.response;

import com.api.model.TransactionTier;
import com.google.gson.Gson;
import org.json.JSONObject;

public class ResponseUpdateTier {
    private String message;
    private TransactionTier before;
    private TransactionTier after;

    public ResponseUpdateTier(String message, TransactionTier before, TransactionTier after) {
        this.setMessage(message);
        this.setBefore(before);
        this.setAfter(after);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransactionTier getBefore() {
        return before;
    }

    public void setBefore(TransactionTier before) {
        this.before = before;
    }

    public TransactionTier getAfter() {
        return after;
    }

    public void setAfter(TransactionTier after) {
        this.after = after;
    }
}
