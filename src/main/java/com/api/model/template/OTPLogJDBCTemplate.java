package com.api.model.template;

import com.api.model.OTPLog;
import com.api.model.apienum.OTPStatus;
import com.api.model.dao.OTPLogDAO;
import com.api.model.mapper.OTPLogMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class OTPLogJDBCTemplate implements OTPLogDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemp;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemp = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(int customerID, String username, String otpNumber, String email, long createdAt, long validUntil, String status) {
        String sql = "insert into `otplog` " +
                "(customerID, username, otpNumber, email, createdAt, validUntil, status) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemp.update(sql, (customerID < 0) ? null : customerID, username, otpNumber, email, createdAt, validUntil, status);

        System.out.println("Created OTP, email = " + email + " otpNumber = " + otpNumber);
    }

    @Override
    public OTPLog getOTP(String username) {
//        Can be optimized on getting customerID from username
        String sql = "select * from otplog " +
                "where username = ? && status = ?";

        return jdbcTemp.queryForObject(sql, new Object[]{username, OTPStatus.PENDING.toString()}, new OTPLogMapper());
    }

    @Override
    public void updateOTP(int otpID, String status) {
        String sql = "update otplog " +
                "set status = ? " +
                "where otpID = ?";

        jdbcTemp.update(sql, status, otpID);
        System.out.println("Updated otpID: " + otpID + " to " + status);
    }
}
