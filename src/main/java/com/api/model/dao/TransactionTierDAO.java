package com.api.model.dao;

import com.api.model.TransactionTier;

import javax.sql.DataSource;
import java.util.List;

public interface TransactionTierDAO {
    public void setDataSource(DataSource ds);
    public void create(int minimumTransaction, int maximumTransaction, float probability, float discount);
    public List<TransactionTier> listTransactionTiers();
    public void update(TransactionTier tier);
    public boolean delete(int tierID);
    public TransactionTier getTier(int tierID);
    public List<TransactionTier> getTierByAmmount(int transactionAmmount);
}