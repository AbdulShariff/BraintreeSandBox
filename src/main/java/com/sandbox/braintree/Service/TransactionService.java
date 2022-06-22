package com.sandbox.braintree.Service;

import com.sandbox.braintree.model.ChargeRequest;
import com.sandbox.braintree.model.TransactionResponse;


public interface TransactionService {

    TransactionResponse charge(ChargeRequest chargeRequest, Integer customersId, String settlement);

    TransactionResponse captureTransaction(String transactionId);

    TransactionResponse refundTransaction(String transactionId);

    TransactionResponse voidTransaction(String transactionId);
}