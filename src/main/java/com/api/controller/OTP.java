package com.api.controller;

import com.api.annotation.CheckSignature;
import com.api.annotation.Logged;
import com.api.annotation.OTPVerifyParam;
import com.api.controller.generalcontrollerinterface.HaveModels;
import com.api.model.OTPLog;
import com.api.model.apienum.OTPStatus;
import com.api.model.dao.OTPLogDAO;
import com.api.model.requestbody.RequestCheckOTP;
import com.api.model.requestbody.RequestCreateOTP;
import com.api.model.response.ResponseCheckOTP;
import com.api.model.response.ResponseCreateOTP;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@CrossOrigin(origins = "http://127.0.0.1:8080")
@RestController
public class OTP implements HaveModels {
    private ModelDI models;
    private String otpKey = "54321";

    @Override
    public ModelDI getModels() {
        return models;
    }

    @Override
    public void setModels(ModelDI models) {
        this.models = models;
    }

    @PostMapping("/otp/create")
    @Logged
    @CheckSignature
    @OTPVerifyParam
    ResponseEntity<ResponseCreateOTP> createOTP(HttpServletRequest request, @RequestBody RequestCreateOTP requestBody) {
        OTPLogDAO OTPLogDB = models.getOTPLogDB();

//        just need to check whether it's still valid or not
//        OTP is checked on python script every ... minutes
        OTPLog otp;
        try {
            otp = OTPLogDB.getOTP(requestBody.getUsername());

//            does validation needed here?
            return new ResponseEntity<>(new ResponseCreateOTP(HttpStatus.ACCEPTED, otp), HttpStatus.ACCEPTED);
        } catch (DataAccessException ignored) { }

        StringBuilder s = new StringBuilder();
        s.append(requestBody.getUsername()).append(requestBody.getEmail()).append(otpKey);
        String otpNumber = DigestUtils.sha256Hex(s.toString());

        long createdAt = Instant.now().getEpochSecond();
        long validUntil = createdAt + 15*60;

//        Can this fail to create?
        OTPLogDB.create(-1, requestBody.getUsername(), otpNumber, requestBody.getEmail(), createdAt, validUntil, OTPStatus.PENDING.toString());
        otp = OTPLogDB.getOTP(requestBody.getUsername());
        return new ResponseEntity<>(new ResponseCreateOTP(HttpStatus.CREATED, otp), HttpStatus.CREATED);
    }

    @PostMapping("/otp/check")
    @Logged
    @CheckSignature
    ResponseEntity<ResponseCheckOTP> checkOTP(HttpServletRequest request, @RequestBody RequestCheckOTP requestBody) {
        OTPLogDAO OTPLogDB = models.getOTPLogDB();

        OTPLog otp;
        try {
            otp = OTPLogDB.getOTP(requestBody.getUsername());
        } catch(DataAccessException e) {
            return new ResponseEntity<>(new ResponseCheckOTP(HttpStatus.BAD_REQUEST, requestBody.getUsername(), "Not Found"), HttpStatus.BAD_REQUEST);
        }

        if(otp.getStatus().equals(OTPStatus.PENDING.toString())) {
//            Valid
            OTPLogDB.updateOTP(otp.getID(), OTPStatus.OK.toString());
            return new ResponseEntity<>(new ResponseCheckOTP(HttpStatus.ACCEPTED, requestBody.getUsername(), OTPStatus.OK.toString()), HttpStatus.ACCEPTED);
        }
        else {
//            Not Valid
            return new ResponseEntity<>(new ResponseCheckOTP(HttpStatus.CONFLICT, requestBody.getUsername(), OTPStatus.NOT_VALID.toString()), HttpStatus.CONFLICT);
        }
    }
}