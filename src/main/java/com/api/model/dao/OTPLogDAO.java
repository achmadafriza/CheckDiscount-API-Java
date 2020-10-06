package com.api.model.dao;

import com.api.model.OTPLog;

import javax.sql.DataSource;

public interface OTPLogDAO {
    public void setDataSource(DataSource ds);
    public void create(int customerID, String username, String otpNumber, String email, long createdAt, long validUntil, String status);
    public OTPLog getOTP(String username);
    public void updateOTP(int otpID, String status);
}
