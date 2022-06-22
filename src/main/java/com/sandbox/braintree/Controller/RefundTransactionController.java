package com.sandbox.braintree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sandbox.braintree.Service.TransactionService;
import com.sandbox.braintree.model.TransactionResponse;

@Controller
@RequestMapping(value = "/payment/v1/transaction")
public class RefundTransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/refund")
    @ResponseBody
    public TransactionResponse refundTransaction(@RequestParam("transaction_id") String transactionId) {
        TransactionResponse response = transactionService.refundTransaction(transactionId);
        return response;
    }
}