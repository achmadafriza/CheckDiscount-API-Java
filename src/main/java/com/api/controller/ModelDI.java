package com.api.controller;

import com.api.model.dao.APILogDAO;
import com.api.model.dao.OTPLogDAO;
import com.api.model.dao.TransactionLogDAO;
import com.api.model.dao.TransactionTierDAO;
import com.api.model.template.APILogJDBCTemplate;
import com.api.model.template.TransactionLogJDBCTemplate;
import com.api.model.template.TransactionTierJDBCTemplate;

public class ModelDI {
    private APILogDAO APILogDB;
    private TransactionLogDAO TransactionLogDB;
    private TransactionTierDAO TierDB;
    private OTPLogDAO OTPLogDB;

    public TransactionTierDAO getTierDB() {
        return TierDB;
    }

    public void setTierDB(TransactionTierJDBCTemplate tierDB) {
        this.TierDB = tierDB;
    }

    public TransactionLogDAO getTransactionLogDB() {
        return TransactionLogDB;
    }

    public void setTransactionLogDB(TransactionLogJDBCTemplate transactionLogDB) {
        this.TransactionLogDB = transactionLogDB;
    }

    public APILogDAO getAPILogDB() {
        return APILogDB;
    }

    public void setAPILogDB(APILogJDBCTemplate APILogDB) {
        this.APILogDB = APILogDB;
    }

    public OTPLogDAO getOTPLogDB() {
        return OTPLogDB;
    }

    public void setOTPLogDB(OTPLogDAO OTPLogDB) {
        this.OTPLogDB = OTPLogDB;
    }
}
