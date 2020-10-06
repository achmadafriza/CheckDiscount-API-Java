package com.api.model.mapper;

import com.api.model.TransactionTier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTierMapper implements RowMapper<TransactionTier> {
    @Override
    public TransactionTier mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TransactionTier tier = new TransactionTier();

        tier.setID(resultSet.getInt("tierID"));
        tier.setMinimumTransaction(resultSet.getInt("minimumTransaction"));
        tier.setMaximumTransaction(resultSet.getInt("maximumTransaction"));
        tier.setProbability(resultSet.getFloat("probability"));
        tier.setDiscount(resultSet.getFloat("discount"));

        return tier;
    }
}
