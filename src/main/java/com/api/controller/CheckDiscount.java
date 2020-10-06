package com.api.controller;

import com.api.annotation.CheckDiscountVerifyParam;
import com.api.annotation.CheckSignature;
import com.api.annotation.Logged;
import com.api.controller.generalcontrollerinterface.HaveModels;
import com.api.model.TransactionLog;
import com.api.model.dao.TransactionTierDAO;
import com.api.model.requestbody.RequestCheckDiscount;
import com.api.model.TransactionTier;
import com.api.model.requestbody.RequestCreateTier;
import com.api.model.requestbody.RequestDeleteTier;
import com.api.model.requestbody.RequestUpdateTier;
import com.api.model.response.ResponseCreateTier;
import com.api.model.response.ResponseDeleteTier;
import com.api.model.response.ResponseGetGeneral;
import com.api.model.response.ResponseUpdateTier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://127.0.0.1:8080")
@RestController
public class CheckDiscount implements HaveModels {
    private ModelDI models;

    public ModelDI getModels() {
        return models;
    }

    public void setModels(ModelDI models) {
        this.models = models;
    }

    @GetMapping("/discountlog")
    ResponseEntity<ResponseGetGeneral> listAllDiscountLogs(HttpServletRequest request) {
        return new ResponseEntity<>(
                new ResponseGetGeneral(models.getTransactionLogDB().listCheckDiscountLogs()),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/apilog")
    ResponseEntity<ResponseGetGeneral> listAllAPILogs(HttpServletRequest request) {
        return new ResponseEntity<>(
                new ResponseGetGeneral(models.getAPILogDB().listAPILogs()),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/tier")
    ResponseEntity<ResponseGetGeneral> listAllTiers(HttpServletRequest request) {
        return new ResponseEntity<>(
                new ResponseGetGeneral(models.getTierDB().listTransactionTiers()),
                HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/tier", params = "id")
    ResponseEntity<ResponseGetGeneral> getTierByID(HttpServletRequest request, @RequestParam int id) {
        List<Object> array = new ArrayList<>();
        try {
            array.add(models.getTierDB().getTier(id));
        } catch (EmptyResultDataAccessException ignored) { }

        return new ResponseEntity<>(new ResponseGetGeneral(array), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/tier", params = "ammount")
    ResponseEntity<ResponseGetGeneral> listTierByAmmount(HttpServletRequest request, @RequestParam int ammount) {
        return new ResponseEntity<>(
                new ResponseGetGeneral(models.getTierDB().getTierByAmmount(ammount)),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/tier")
    @Logged
    @CheckSignature
    ResponseEntity<ResponseDeleteTier> deleteTierByID(HttpServletRequest request, RequestDeleteTier requestParam) {
        boolean deleted = models.getTierDB().delete(requestParam.getId());
        return new ResponseEntity<>(new ResponseDeleteTier(requestParam.getId(), deleted), HttpStatus.ACCEPTED);
    }

    @PostMapping("/tier")
    @Logged
    @CheckSignature
    ResponseEntity<ResponseCreateTier> createTier(HttpServletRequest request, @RequestBody RequestCreateTier requestBody) {
        TransactionTierDAO TierDB = models.getTierDB();
        TierDB.create(requestBody.getMinimumTransaction(), requestBody.getMaximumTransaction(),
                requestBody.getProbability(), requestBody.getDiscount());

        return new ResponseEntity<>(new ResponseCreateTier(HttpStatus.CREATED, requestBody), HttpStatus.CREATED);
    }

    @PutMapping("/tier")
    @Logged
    @CheckSignature
    ResponseEntity<ResponseUpdateTier> updateTier(HttpServletRequest request, @RequestBody RequestUpdateTier requestBody) {
        TransactionTierDAO tierDB = models.getTierDB();

        TransactionTier tierBefore = tierDB.getTier(requestBody.getId());
        TransactionTier tierAfter = new TransactionTier(requestBody);

//        May have DataAccessException, not handled
        tierDB.update(tierAfter);
        return new ResponseEntity<>(new ResponseUpdateTier("Success updating Tier", tierBefore, tierAfter),
                HttpStatus.CREATED);
    }

    @PostMapping("/checkdiscount")
    @Logged
    @CheckDiscountVerifyParam
    @CheckSignature
    ResponseEntity<TransactionLog> checkDiscount(HttpServletRequest request, @RequestBody RequestCheckDiscount requestBody) {
        try {
            TransactionLog currentObj = models.getTransactionLogDB().get(requestBody.getUsername(), requestBody.getAmmount(), requestBody.getTime());
            return new ResponseEntity<>(currentObj, HttpStatus.ACCEPTED);
        } // When Data is not available, request is processed.
        catch(DataAccessException ignored) {}

        Random rand = new Random();

        List<TransactionTier> tiers = models.getTierDB().getTierByAmmount(requestBody.getAmmount());
        int customerID = -1;
        try {
//            What if there's no tier that match? throws IllegalArgumentException
            TransactionTier tier = tiers.get(rand.nextInt(tiers.size()));
            if(rand.nextFloat() <= tier.getProbability()) {
//            Get CustomerID from customerData
                models.getTransactionLogDB().create(customerID, tier.getID(), requestBody.getUsername(), true, requestBody.getAmmount(),
                        (int) (requestBody.getAmmount() * tier.getDiscount()), requestBody.getTime());
            }
            else {
                models.getTransactionLogDB().create(customerID, tier.getID(), requestBody.getUsername(), false, requestBody.getAmmount(),
                        -1, requestBody.getTime());
            }
        } catch (IllegalArgumentException e) {
//            -1 = null
            models.getTransactionLogDB().create(customerID, -1, requestBody.getUsername(), false, requestBody.getAmmount(),
                    -1, requestBody.getTime());
        }

        //            Can still be optimized, now searches per row by username etc.
        TransactionLog currentObj = models.getTransactionLogDB().get(
                requestBody.getUsername(), requestBody.getAmmount(), requestBody.getTime()
        );
        return new ResponseEntity<>(currentObj, HttpStatus.CREATED);
    }
}