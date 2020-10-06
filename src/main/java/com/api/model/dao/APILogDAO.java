package com.api.model.dao;

import com.api.model.APILog;

import javax.sql.DataSource;
import java.util.List;

public interface APILogDAO {
    public void setDataSource(DataSource ds);
    public void create(String ip, int responseID,
                       String requestType, String requestHeader, String requestBody,
                       String responseHeader, String responseBody,
                       long timeRequest, long timeResponse, long elapsedTime, String status, String statusDetails);
    public List<APILog> listAPILogs();
}
