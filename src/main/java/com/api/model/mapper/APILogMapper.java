package com.api.model.mapper;

import com.api.model.APILog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class APILogMapper implements RowMapper<APILog> {
    @Override
    public APILog mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        APILog log = new APILog();

        log.setID(resultSet.getInt("logID"));
        log.setIp(resultSet.getString("ip"));
        log.setRequestType(resultSet.getString("requestType"));
        log.setRequestHeader(resultSet.getString("requestHeader"));
        log.setRequestBody(resultSet.getString("requestBody"));
        log.setResponseHeader(resultSet.getString("responseHeader"));
        log.setResponseBody(resultSet.getString("responseBody"));
        log.setTimeRequest(resultSet.getLong("timeRequest"));
        log.setTimeResponse(resultSet.getLong("timeResponse"));
        log.setElapsedTime(resultSet.getLong("elapsedTime"));
        log.setStatus(resultSet.getString("status"));
        log.setStatusDetails(resultSet.getString("statusDetails"));

        return log;
    }
}
