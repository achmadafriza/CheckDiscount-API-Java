package com.api.model.response;

public class ResponseDeleteTier {
    private int id;
    private boolean deleted;

    public ResponseDeleteTier(int id, boolean deleted) {
        setId(id);
        setDeleted(deleted);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
