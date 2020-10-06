package com.api.model.response;

import com.api.model.OTPLog;
import org.springframework.http.HttpStatus;

public class ResponseCreateOTP {
    private HttpStatus status;
    private String username;
    private String email;
    private long createdAt;
    private long validUntil;

    public ResponseCreateOTP(HttpStatus status, OTPLog otp) {
        setStatus(status);
        setUsername(otp.getUsername());
        setEmail(otp.getEmail());
        setCreatedAt(otp.getCreatedAt());
        setValidUntil(otp.getValidUntil());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(long validUntil) {
        this.validUntil = validUntil;
    }
}
