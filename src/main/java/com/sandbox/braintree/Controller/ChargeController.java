package com.sandbox.braintree.Controller;

import com.sandbox.braintree.model.ChargeRequest;
import com.sandbox.braintree.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sandbox.braintree.Service.TransactionService;

@Controller
@RequestMapping(value = "/payment/v1/transaction")
public class ChargeController {
    @Autowired
    TransactionService transactionService;
    @PostMapping(value = "/init")
    @ResponseBody
    public TransactionResponse charge(@RequestBody ChargeRequest chargeRequest, @RequestParam("cus_id") Integer customersId, @RequestParam("settlement") String settlement) {
        TransactionResponse response = transactionService.charge(chargeRequest, customersId, settlement);
        return response;
    }
}
