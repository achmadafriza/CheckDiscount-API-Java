package com.api.model.template;

import com.api.model.TransactionTier;
import com.api.model.dao.TransactionTierDAO;
import com.api.model.mapper.TransactionTierMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TransactionTierJDBCTemplate implements TransactionTierDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemp;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemp = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(int minimumTransaction, int maximumTransaction, float probability, float discount) {
        String sql = "insert into `transactiontier` (`minimumTransaction`, `maximumTransaction`, `probability`, `discount`) " +
                "values (?, ?, ?, ?)";
        jdbcTemp.update(sql, minimumTransaction, maximumTransaction, probability, discount);

        System.out.println("Created TransactionTier, probability = " + probability);
    }

    @Override
    public List<TransactionTier> listTransactionTiers() {
        String sql = "select * from `transactiontier`";
        return jdbcTemp.query(sql, new TransactionTierMapper());
    }

    @Override
    public TransactionTier getTier(int tierID) {
        String sql = "select * from `transactiontier` where `tierID` = ?";
        return jdbcTemp.queryForObject(sql, new Object[]{tierID}, new TransactionTierMapper());
    }

    @Override
    public List<TransactionTier> getTierByAmmount(int transactionAmmount) {
        String sql = "select * from transactiontier " +
                "where minimumTransaction <= ? and maximumTransaction >= ?";
        return jdbcTemp.query(sql, new Object[]{transactionAmmount, transactionAmmount}, new TransactionTierMapper());
    }

    @Override
    public boolean delete(int tierID) {
        String sql = "delete from `TransactionTier` where tierID = ?";

        if(jdbcTemp.update(sql, tierID) == 1) {
            System.out.println("Deleted TransactionTier with tierID = " + tierID);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void update(TransactionTier tier) {
        String sql = "update `transactiontier` " +
                "set `minimumTransaction` = ?, `maximumTransaction` = ?, `probability` = ?, `discount` = ? " +
                "where `tierID` = ?";
        jdbcTemp.update(sql, tier.getMinimumTransaction(), tier.getMaximumTransaction(),
                tier.getProbability(), tier.getDiscount(), tier.getID());

        System.out.println("Updated TransactionTier with tierID = " + tier.getID());
    }
}
