package com.api.controller.advice;

import com.api.controller.CheckDiscount;
import com.api.controller.generalcontrollerinterface.HaveHttpStatus;
import com.api.controller.generalcontrollerinterface.HaveModels;
import com.api.model.TransactionLog;
import com.api.model.apienum.RequestType;
import com.api.model.dao.APILogDAO;
import com.api.model.generalmodelinterface.HaveID;
import com.api.model.template.APILogJDBCTemplate;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Order(1)
public class LoggingAspect {
    private APILogDAO APILogDB;
    private long timeRequested;
    private long timeResponded;

    public APILogDAO getAPILogDB() {
        return APILogDB;
    }

    public void setAPILogDB(APILogJDBCTemplate APILogDB) {
        this.APILogDB = APILogDB;
    }

    private static JSONObject getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        JSONObject json = new JSONObject(map);
        return json;
    }

    private static String getIP(HttpServletRequest request) {
        String ip = "";

        if (request != null) {
            ip = request.getHeader("X-FORWARDED-FOR");
            if (ip == null || "".equals(ip)) {
                ip = request.getRemoteAddr();
            }
        }

        return ip;
    }

//    @Pointcut("execution(* com.api.controller.advice.APIGlobalExceptionHandler.*(..))")
//    private void logError() {}
    @Before("@annotation(com.api.annotation.ErrorLogged)")
    public void logError(JoinPoint joinPoint) throws IOException {
        System.out.println("Before Error Logging:");
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        Exception ex = (Exception) joinPoint.getArgs()[0];

//        Still Buggy
        String requestBody = "Error at processing request";
//        requestBody = IOUtils.toString(request.getInputStream());

        JSONObject responseBody = new JSONObject();
        int statusCode = ((HaveHttpStatus) ex).getStatus().value();
        String statusPhrase = ((HaveHttpStatus) ex).getStatus().getReasonPhrase();
        responseBody.put("status", statusCode);
        responseBody.put("error", statusPhrase);
        responseBody.put("exception", ex.getClass().toString());
        responseBody.put("message", ex.getMessage());

//        How u get responseHeader la
//        U get request body by passing request body to exception and getting it.
        APILogDB.create(getIP(request), -1, RequestType.ERROR.toString(),
                getHeadersInfo(request).toString(), requestBody,
                "---", responseBody.toString(),
                timeRequested, timeResponded, timeResponded - timeRequested,
                statusCode+"", statusPhrase);
    }

    @Around("@annotation(com.api.annotation.Logged)")
    public ResponseEntity<Object> log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before Logging:");
        timeRequested = Instant.now().toEpochMilli();

        ResponseEntity<Object> value = null;
        try {
            value = (ResponseEntity<Object>) proceedingJoinPoint.proceed();

            System.out.println("After Logging:");
            timeResponded = Instant.now().toEpochMilli();
            HttpServletRequest request = (HttpServletRequest) proceedingJoinPoint.getArgs()[0];
            HttpStatus status = value.getStatusCode();

            String requestType = "";
            if(proceedingJoinPoint.getSignature().getName().equals("checkDiscount")) {
                requestType = RequestType.TRANSACTION.toString();
            } else if(proceedingJoinPoint.getSignature().getDeclaringType().equals(CheckDiscount.class)) {
                requestType = RequestType.REQUEST_DATA.toString();
            }

//            u need to verify by regex, except checkDiscount
            JSONObject requestBodyJSON;
            if((proceedingJoinPoint.getArgs()).length == 2) {
                requestBodyJSON = new JSONObject(proceedingJoinPoint.getArgs()[1]);
            } else {
                requestBodyJSON = null;
            }

//            Still Buggy
            HttpHeaders responseHeader = value.getHeaders();
            JSONObject responseHeaderJSON = new JSONObject(responseHeader.toSingleValueMap());

            Object responseBody = value.getBody();
            JSONObject responseBodyJSON;
            if(responseBody instanceof List) {
                String list = new Gson().toJson(responseBody);
                responseBodyJSON = new JSONObject();
                responseBodyJSON.put("list", list);
            } else {
                responseBodyJSON = new JSONObject(responseBody);
            }

            APILogDB.create(getIP(request), (responseBody instanceof HaveID) ? ( (HaveID) responseBody).getID() : -1,
                    requestType, getHeadersInfo(request).toString(),
                    (requestBodyJSON != null) ? requestBodyJSON.toString() : "",
                    responseHeaderJSON.toString(), responseBodyJSON.toString(),
                    timeRequested, timeResponded, timeResponded - timeRequested,
                    status.value()+"", status.getReasonPhrase());
        } catch(Throwable throwable) {
            System.out.println("After Error Logging:");
            timeResponded = Instant.now().toEpochMilli();
            throw throwable;
        }

        return value;
    }

//    @Before("logAPI()")
//    public void logBefore() {
//        System.out.println("Before Advice:");
//        timeRequested = Instant.now().toEpochMilli();
//    }
//
//    @AfterReturning(pointcut = "logAPI()", returning = "retVal")
//    public void logAfter(JoinPoint joinPoint, ResponseEntity<TransactionLog> retVal) {
//        System.out.println("After Advice:");
//        timeResponded = Instant.now().toEpochMilli();
//
//        CheckDiscount obj = (CheckDiscount) joinPoint.getThis();
//        APILogDAO APILogDB = obj.getAPILogDB();
//
//        TransactionLog discountLog = retVal.getBody();
//
//        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
//        String ip = "";
//
//        if (request != null) {
//            ip = request.getHeader("X-FORWARDED-FOR");
//            if (ip == null || "".equals(ip)) {
//                ip = request.getRemoteAddr();
//            }
//        }
//
//        HttpStatus status = retVal.getStatusCode();
//        APILogDB.create(ip, discountLog.getResponseID(), timeRequested, timeResponded,
//                timeResponded - timeRequested, ""+status.value(), status.getReasonPhrase());
//    }
}
