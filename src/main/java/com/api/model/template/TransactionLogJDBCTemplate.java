package com.api.model.template;

import com.api.model.TransactionLog;
import com.api.model.dao.TransactionLogDAO;
import com.api.model.mapper.TransactionLogMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TransactionLogJDBCTemplate implements TransactionLogDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemp;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemp = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(int customerID, int tierID, String username, boolean discounted, int transactionAmmount, int discountedAmmount, long transactionTime) {
        String sql = "insert into `transactionlog` " +
                "(`customerID`, `tierID`, `username`, `discounted`, " +
                "`transactionAmmount`, `discountedAmmount`, `transactionTime`) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemp.update(sql, customerID, (tierID <= 0) ? null : tierID, username, discounted, transactionAmmount,
                (discountedAmmount <= 0) ? null : discountedAmmount, transactionTime);

        System.out.println("Created TransactionLog, username = " + username + " discounted = " + discounted);
    }


//    Currently not optimized, not searching by index
    @Override
    public TransactionLog get(String username, int transactionAmmount, long transactionTime) {
//        String sql = "select * from `customerdata` where username = ?";
        String sql = "select * from `transactionlog` where username = ? and transactionAmmount = ? and transactionTime = ?";
        return jdbcTemp.queryForObject(sql, new Object[]{username, transactionAmmount, transactionTime}, new TransactionLogMapper());
    }

    @Override
    public List<TransactionLog> listCheckDiscountLogs() {
        String sql = "select * from `transactionlog`";
        return jdbcTemp.query(sql, new TransactionLogMapper());
    }
}
