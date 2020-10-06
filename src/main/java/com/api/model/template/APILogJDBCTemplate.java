package com.api.model.template;

import com.api.model.APILog;
import com.api.model.dao.APILogDAO;
import com.api.model.mapper.APILogMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class APILogJDBCTemplate implements APILogDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemp;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemp = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String ip, int responseID, String requestType,
                       String requestHeader, String requestBody, String responseHeader, String responseBody,
                       long timeRequest, long timeResponse, long elapsedTime, String status, String statusDetails) {
        String sql = "insert into `apilog` " +
                "(`ip`, `responseID`, `requestType`, `requestHeader`, `requestBody`, `responseHeader`, `responseBody`," +
                "`timeRequest`, `timeResponse`, `elapsedTime`, `status`, `statusDetails`) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemp.update(sql, ip, (responseID < 0) ? null : responseID, requestType,
                requestHeader, requestBody, responseHeader, responseBody,
                timeRequest, timeResponse, elapsedTime, status, statusDetails);

        System.out.println("Created APILog, ip = " + ip + " status = " + status);
    }

    @Override
    public List<APILog> listAPILogs() {
        String sql = "select * from `apilog`";
        return jdbcTemp.query(sql, new APILogMapper());
    }
}
