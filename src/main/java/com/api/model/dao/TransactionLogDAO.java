package com.api.model.dao;

import com.api.model.TransactionLog;

import javax.sql.DataSource;
import java.util.List;

public interface TransactionLogDAO {
    public void setDataSource(DataSource ds);
    public void create(int customerID, int tierID, String username,
                       boolean discounted, int transactionAmmount, int discountedAmmount, long transactionTime);
    public TransactionLog get(String username, int transactionAmmount, long transactionTime);
    public List<TransactionLog> listCheckDiscountLogs();
}
