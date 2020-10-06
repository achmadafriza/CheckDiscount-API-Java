package com.api.model.mapper;

import com.api.model.OTPLog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OTPLogMapper implements RowMapper<OTPLog> {
    @Override
    public OTPLog mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OTPLog otp = new OTPLog();

        otp.setID(resultSet.getInt("otpID"));
        otp.setCustomerID(resultSet.getInt("customerID"));
        otp.setUsername(resultSet.getString("username"));
        otp.setOtpNumber(resultSet.getString("otpNumber"));
        otp.setEmail(resultSet.getString("email"));
        otp.setCreatedAt(resultSet.getLong("createdAt"));
        otp.setValidUntil(resultSet.getLong("validUntil"));
        otp.setStatus(resultSet.getString("status"));

        return otp;
    }
}
