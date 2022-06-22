package com.sandbox.braintree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sandbox.braintree.model.TransactionResponse;
import com.sandbox.braintree.Service.TransactionService;

@Controller
@RequestMapping(value = "/payment/v1/transaction")
public class CaptureTransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping(value = "/capture")
    @ResponseBody
    public TransactionResponse captureTransaction(@RequestParam("transaction_id") String transactionId) {
        TransactionResponse response = transactionService.captureTransaction(transactionId);
        return response;
    }
}

