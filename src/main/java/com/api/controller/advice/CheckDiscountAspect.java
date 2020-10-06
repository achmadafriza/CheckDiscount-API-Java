package com.api.controller.advice;

import com.api.controller.error.AmmountExceedException;
import com.api.controller.error.DateIsNotTodayException;
import com.api.controller.error.InvalidCheckDiscountParameter;
import com.api.model.requestbody.RequestCheckDiscount;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Aspect
@Order(3)
public class CheckDiscountAspect {
//    Need to check all parameter names
    @Before("@annotation(com.api.annotation.CheckDiscountVerifyParam)")
    public void verifyRequestBody(JoinPoint joinPoint)
            throws AmmountExceedException, DateIsNotTodayException, ArrayIndexOutOfBoundsException {
        System.out.println("Verify Advice:");
        RequestCheckDiscount request = (RequestCheckDiscount) joinPoint.getArgs()[1];

        if(request.getUsername().isEmpty()) {
            throw new InvalidCheckDiscountParameter(HttpStatus.BAD_REQUEST);
        }

        if(request.getAmmount() < 10000 || request.getAmmount() > 20000000) {
            throw new AmmountExceedException(request.getAmmount(), HttpStatus.BAD_REQUEST);
        }

        LocalDate time = Instant.ofEpochSecond(request.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        if(!time.equals(LocalDate.now())) {
            throw new DateIsNotTodayException(LocalDate.now(), HttpStatus.BAD_REQUEST);
        }
    }
}
