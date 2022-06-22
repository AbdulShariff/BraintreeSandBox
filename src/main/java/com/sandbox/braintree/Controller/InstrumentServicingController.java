package com.sandbox.braintree.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sandbox.braintree.Service.InitializeService;
import com.sandbox.braintree.model.PaymentMethodResponse;

@Controller
@RequestMapping(value = "/payment/v1/instrument")
public class InstrumentServicingController {

    @Autowired
    private InitializeService initializeService;

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public PaymentMethodResponse delete(@RequestParam("cus_id") Integer customersId) {
        PaymentMethodResponse response = initializeService.deleteCustomer(customersId);
        return response;
    }

    @GetMapping(value = "/find")
    @ResponseBody
    public PaymentMethodResponse find(@RequestParam("cus_id") Integer customersId) {
        PaymentMethodResponse response = initializeService.findCustomer(customersId);
        return response;
    }
}
