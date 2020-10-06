package com.api.controller.error;

import org.springframework.http.HttpStatus;

public class InvalidEmail extends RuntimeException{
    private HttpStatus status;

    public InvalidEmail(String email, HttpStatus status) {
        super("Email " + email + " is not valid!");
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
