package com.api.model.mapper;

import com.api.model.TransactionLog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionLogMapper implements RowMapper<TransactionLog> {
    @Override
    public TransactionLog mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        TransactionLog log = new TransactionLog();

        log.setID(resultSet.getInt("transactionID"));
        log.setCustomerID(resultSet.getInt("customerID"));
        log.setTierID(resultSet.getInt("tierId"));
        log.setUsername(resultSet.getString("username"));
        log.setDiscounted(resultSet.getBoolean("discounted"));
        log.setTransactionAmmount(resultSet.getInt("transactionAmmount"));
        log.setDiscountedAmmount(resultSet.getInt("discountedAmmount"));
        log.setTransactionTime(resultSet.getLong("transactionTime"));

        return log;
    }
}
