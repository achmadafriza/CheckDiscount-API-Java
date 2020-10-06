package com.api.controller.advice;

import com.api.controller.error.InvalidSignature;
import com.api.model.generalmodelinterface.HaveSignature;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

@Aspect
@Order(2)
public class CheckSignatureAspect {
    @Before("@annotation(com.api.annotation.CheckSignature)")
    public void verifySignature(JoinPoint joinPoint) throws InvalidSignature {
        HaveSignature obj = (HaveSignature) joinPoint.getArgs()[1];
        String digestedSignature = DigestUtils.sha256Hex(obj.getAllParams() + obj.getKey());

        System.out.println("obj signature: " + obj.getSignature());
        System.out.println(obj.getAllParams() + obj.getKey());
        System.out.println("created signature: " + digestedSignature);
        if(!digestedSignature.equals(obj.getSignature())) {
            throw new InvalidSignature(HttpStatus.BAD_REQUEST);
        }
    }
}
