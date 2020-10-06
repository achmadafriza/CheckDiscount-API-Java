package com.api.controller.error;

import com.api.controller.generalcontrollerinterface.HaveHttpStatus;
import org.springframework.http.HttpStatus;

public class InvalidCheckDiscountParameter extends RuntimeException implements HaveHttpStatus {
    private HttpStatus status;

    public InvalidCheckDiscountParameter(HttpStatus status) {
        super("Request must only contain username, ammount, and time");
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
