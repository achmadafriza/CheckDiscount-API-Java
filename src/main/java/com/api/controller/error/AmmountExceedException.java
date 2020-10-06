package com.api.controller.error;

import com.api.controller.generalcontrollerinterface.HaveHttpStatus;
import org.springframework.http.HttpStatus;

public class AmmountExceedException extends RuntimeException implements HaveHttpStatus {
    private HttpStatus status;

    public AmmountExceedException(int ammount, HttpStatus status) {
        super(ammount + " exceeds parameters Rp.10.000 and Rp.20.000.000");
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}