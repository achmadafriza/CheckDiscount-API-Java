package com.api.controller.advice;

import com.api.annotation.ErrorLogged;
import com.api.controller.error.AmmountExceedException;
import com.api.controller.error.DateIsNotTodayException;
import com.api.controller.error.InvalidCheckDiscountParameter;
import com.api.controller.error.InvalidSignature;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class APIGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DateIsNotTodayException.class)
    @ErrorLogged
    public void adviceDateIsNotToday(DateIsNotTodayException ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(AmmountExceedException.class)
    @ErrorLogged
    public void adviceAmmountExceed(AmmountExceedException ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendError(ex.getStatus().value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidCheckDiscountParameter.class)
    @ErrorLogged
    public void adviceBadRequest(InvalidCheckDiscountParameter ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendError(ex.getStatus().value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidSignature.class)
    @ErrorLogged
    public void adviceBadSignature(InvalidSignature ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendError(ex.getStatus().value(), ex.getMessage());
    }
}
