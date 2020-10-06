package com.api.controller.error;

import com.api.controller.generalcontrollerinterface.HaveHttpStatus;
import org.springframework.http.HttpStatus;

public class InvalidSignature extends RuntimeException implements HaveHttpStatus {
    HttpStatus status;

    public InvalidSignature(HttpStatus status) {
        super("Signature is Invalid.");
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
