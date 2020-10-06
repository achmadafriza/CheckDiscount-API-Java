package com.api.model.response;

import org.springframework.http.HttpStatus;

public class ResponseCheckOTP {
    private HttpStatus status;
    private String username;
    private String currentOTPStatus;

    public ResponseCheckOTP(HttpStatus status, String username, String currentOTPStatus) {
        setStatus(status);
        setUsername(username);
        setCurrentOTPStatus(currentOTPStatus);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getCurrentOTPStatus() {
        return currentOTPStatus;
    }

    public void setCurrentOTPStatus(String currentOTPStatus) {
        this.currentOTPStatus = currentOTPStatus;
    }
}