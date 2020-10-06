package com.api.controller.advice;

import com.api.controller.error.InvalidEmail;
import com.api.model.requestbody.RequestCheckDiscount;
import com.api.model.requestbody.RequestCreateOTP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

@Aspect
@Order(3)
public class OTPAspect {
    public static boolean emailIsValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


    @Before("@annotation(com.api.annotation.OTPVerifyParam)")
    public void verifyRequestBody(JoinPoint joinPoint) {
        System.out.println("Verify Advice:");
        RequestCreateOTP request = (RequestCreateOTP) joinPoint.getArgs()[1];

        if(!emailIsValid(request.getEmail())) {
            throw new InvalidEmail(request.getEmail(), HttpStatus.BAD_REQUEST);
        }
    }
}
