package com.sandbox.braintree.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sandbox.braintree.Service.InitializeService;
import com.sandbox.braintree.model.PaymentMethodResponse;
import com.sandbox.braintree.model.AddInstrumentRequest;
import com.sandbox.braintree.Config.BraintreeGatewayConfig;

@Controller
@RequestMapping(value = "/payment/instrument/v1")
public class InstrumentOnboardingController {
    @Autowired
    private BraintreeGatewayConfig braintreeGatewayConfig;

    @Autowired
    private InitializeService initializeService;

    @GetMapping(value = "/StorePaymentMethod")
    public String StorePaymentMethod(Model model, @RequestParam("cus_id") Integer customersId) {
        String clientToken = braintreeGatewayConfig.getBraintreeGatewayInstance().clientToken().generate();
        model.addAttribute("clientToken", clientToken);
        model.addAttribute("cus_id", customersId);
        return "checkouts/welcome";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public PaymentMethodResponse addMethodOfPayment(@RequestParam("payment_method_nonce") String nonce, @RequestParam("cus_id") Integer customersId) {
        AddInstrumentRequest addInstrumentRequest = new AddInstrumentRequest();
        PaymentMethodResponse response = initializeService.onboard(addInstrumentRequest, nonce, customersId);
        return response;
    }

    @PostMapping(value = "/customer")
    @ResponseBody
    public PaymentMethodResponse addCustomer(@RequestBody AddInstrumentRequest addInstrumentRequest) {
        PaymentMethodResponse response = initializeService.addCustomer(addInstrumentRequest);
        return response;
    }
}