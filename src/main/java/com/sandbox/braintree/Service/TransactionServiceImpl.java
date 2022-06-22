package com.sandbox.braintree.Service;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sandbox.braintree.model.ChargeRequest;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;
import com.sandbox.braintree.Config.BraintreeGatewayConfig;
import com.sandbox.braintree.Repository.TransactionRepository;
import com.sandbox.braintree.model.TransactionEntity;
import com.sandbox.braintree.model.TransactionResponse;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private BraintreeGatewayConfig braintreeGatewayConfig;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponse charge(ChargeRequest chargeRequest, Integer cus_id, String settlement) {
        TransactionRequest request = new TransactionRequest()
                .amount(chargeRequest.getAmount())
                .paymentMethodToken(chargeRequest.getTokenId())
                .options()
                .submitForSettlement(Boolean.valueOf(settlement))
                .done();

        Result<Transaction> result=braintreeGatewayConfig.getBraintreeGatewayInstance().transaction().sale(request);
        TransactionEntity transactionObj=new TransactionEntity();
        String transactionId=result.getTarget().getId();
        String encodedString = Base64.getEncoder().encodeToString(chargeRequest.getTokenId().getBytes());
        transactionObj.setAmount(result.getTarget().getAmount());
        transactionObj.setTransactionDate(String.valueOf(result.getTarget().getCreatedAt().getTime()));
        transactionObj.setCustomerId(cus_id);
        transactionObj.setStatus(String.valueOf(result.getTarget().getStatus()));
        transactionObj.setPaymentType(result.getTarget().getPaymentInstrumentType());
        transactionObj.setTokenId(encodedString);
        transactionObj.setTransactionId(transactionId);
        transactionRepository.save(transactionObj);
        TransactionResponse response=new TransactionResponse();
        if (result.isSuccess()) {
            response.setMessage("Transaction successful");
            response.setTransactionId(transactionId);
        } else {
            response.setMessage("Transaction failed");
        }
        return response;
    }

    @Override
    public TransactionResponse captureTransaction(String transactionId) {  //change transaction status from authoried to submitforsettlement
        Result<Transaction> result = braintreeGatewayConfig.getBraintreeGatewayInstance().transaction().submitForSettlement(transactionId);
        TransactionResponse response=new TransactionResponse();
        if (result.isSuccess()) {
            Transaction settledTransaction = result.getTarget();
            response.setTransactionId(transactionId);
            TransactionEntity transobj=transactionRepository.findByTransactionId(transactionId);
            transobj.setStatus(String.valueOf(result.getTarget().getStatus()));
            transactionRepository.save(transobj);
        } else {
            System.out.println(result.getErrors());
        }
        response.setTransactionId(transactionId);
        response.setMessage("Ok");
        response.setTransactionStatus(String.valueOf(result.getTarget().getStatus()));
        return response;
    }

    @Override
    public TransactionResponse refundTransaction(String transactionId) {
        Result<Transaction> result = braintreeGatewayConfig.getBraintreeGatewayInstance().transaction().refund(transactionId);
        TransactionResponse response=new TransactionResponse();
        if(result.isSuccess()) {
            TransactionEntity transobj=transactionRepository.findByTransactionId(transactionId);
            transobj.setStatus(String.valueOf(result.getTarget().getStatus()));
            transactionRepository.save(transobj);
            response.setTransactionId(transactionId);
            response.setMessage("The given transaction has been refunded");
        } else {
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                System.out.println(error.getMessage());
            }
            response.setMessage("Unable to refund the transaction");
        }
        return response;
    }

    @Override
    public TransactionResponse voidTransaction(String transactionId) {
        Result<Transaction> result = braintreeGatewayConfig.getBraintreeGatewayInstance().transaction().voidTransaction(transactionId);
        TransactionResponse response=new TransactionResponse();
        if (result.isSuccess()) {
            TransactionEntity transobj=transactionRepository.findByTransactionId(transactionId);
            transobj.setStatus(String.valueOf(result.getTarget().getStatus()));
            transactionRepository.save(transobj);
            response.setMessage("the transaction has been voided");
        } else {
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                System.out.println(error.getMessage());
            }
            response.setMessage("the transaction can not be voided");
        }
        return response;
    }

}