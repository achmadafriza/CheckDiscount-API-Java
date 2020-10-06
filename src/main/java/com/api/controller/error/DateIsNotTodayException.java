package com.api.controller.error;

import com.api.controller.generalcontrollerinterface.HaveHttpStatus;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class DateIsNotTodayException extends RuntimeException implements HaveHttpStatus {
    private HttpStatus status;

    public DateIsNotTodayException(LocalDate date, HttpStatus status) {
        super("Transaction Date must be:" + date.toString());
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
