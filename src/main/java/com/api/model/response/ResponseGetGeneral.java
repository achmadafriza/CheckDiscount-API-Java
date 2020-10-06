package com.api.model.response;

import java.util.List;

public class ResponseGetGeneral {
    private int length;
    private List<?> items;

    public ResponseGetGeneral(List<?> items) {
        setItems(items);
        setLength(items.size());
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
