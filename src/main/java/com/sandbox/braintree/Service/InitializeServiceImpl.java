package com.sandbox.braintree.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sandbox.braintree.Config.BraintreeGatewayConfig;
import com.sandbox.braintree.Repository.DeletePaymentMethod;
import com.sandbox.braintree.model.AddInstrumentEntity;
import com.sandbox.braintree.model.PaymentMethodResponse;
import com.sandbox.braintree.Repository.PaymentMethodRepository;
import com.sandbox.braintree.model.AddInstrumentRequest;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.PaymentMethod;
import com.braintreegateway.PaymentMethodRequest;
import com.braintreegateway.Result;
import com.braintreegateway.Customer;

@Service
public class InitializeServiceImpl implements InitializeService {

    @Autowired
    private BraintreeGatewayConfig braintreeGatewayConfig;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private DeletePaymentMethod deletePaymentMethod;

    @Override
    @ResponseBody
    public PaymentMethodResponse onboard(AddInstrumentRequest addInstrumentRequest, @RequestParam("payment_method_nonce") String nonce, Integer id) {
        System.out.println(nonce);
        PaymentMethodResponse response = new PaymentMethodResponse();
        BraintreeGateway braintreeGatewayInstance = braintreeGatewayConfig.getBraintreeGatewayInstance();
        String id1 = String.valueOf(id);
        PaymentMethodRequest paymentMethodRequest =
                new PaymentMethodRequest()
                        .customerId(id1)
                        .paymentMethodNonce(nonce)
                        .options()
                        .makeDefault(addInstrumentRequest.isDefault())
                        .done();

        Result<? extends PaymentMethod> paymentMethodResult =
                braintreeGatewayConfig.getBraintreeGatewayInstance().paymentMethod()
                        .create(paymentMethodRequest);
        String permanentPaymentToken = paymentMethodResult.getTarget().getToken();
        PaymentMethod paymentMethod = braintreeGatewayConfig.getBraintreeGatewayInstance().paymentMethod().find(permanentPaymentToken);
        String type = String.valueOf(paymentMethod.getClass());
        AddInstrumentEntity payobj = paymentMethodRepository.findByCustomersId(id);
        payobj.setIsDefault(paymentMethodResult.getTarget().isDefault());
        payobj.setPaymentType(type);
        paymentMethodRepository.save(payobj);
        if (paymentMethodResult.isSuccess()) {
            response.setCode("200");
            response.setMessage("Ok");
            response.setCustomerId(id);
             response.setType(type);
        } else {
            response.setCode("400");
            response.setMessage("Not Ok");
        }
        return response;
    }

    @Override
    public PaymentMethodResponse addCustomer(AddInstrumentRequest addInstrumentRequest) {
        PaymentMethodResponse response = new PaymentMethodResponse();
        CustomerRequest customerRequest = new CustomerRequest()
                .firstName(addInstrumentRequest.getFirstName())
                .lastName(addInstrumentRequest.getLastName())
                .company(addInstrumentRequest.getCompany())
                .email(addInstrumentRequest.getEmail())
                .phone(addInstrumentRequest.getPhone());
        AddInstrumentEntity payobj = new AddInstrumentEntity();

        Result<Customer> customerResult =
                braintreeGatewayConfig.getBraintreeGatewayInstance().customer()
                        .create(customerRequest);
        int braintreeId;
        if (customerResult.isSuccess()) {
            braintreeId = Integer.parseInt(customerResult.getTarget().getId());
            response.setCode("200");
            response.setMessage("Ok");
        } else {
            response.setCode("400");
            response.setMessage("Not Ok");
            throw new RuntimeException("Failed to create Customer!!");
        }
        try {
            payobj.setCustomersId(braintreeId);
            payobj.setFirstName(customerResult.getTarget().getFirstName());
            payobj.setLastName(customerResult.getTarget().getLastName());
            payobj.setEmail(customerResult.getTarget().getEmail());
            payobj.setCompany(customerResult.getTarget().getCompany());
            payobj.setPhone(customerResult.getTarget().getPhone());
            paymentMethodRepository.save(payobj);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        response.setCustomerId(braintreeId);
        response.setFirstname(customerResult.getTarget().getFirstName());
        response.setLastname(customerResult.getTarget().getLastName());
        return response;
    }

    @Override
    public PaymentMethodResponse deleteCustomer(Integer id) {
        PaymentMethodResponse paymentMethodResponse = new PaymentMethodResponse();
        AddInstrumentEntity payobj = paymentMethodRepository.findByCustomersId(id);
        paymentMethodRepository.delete(payobj);
        paymentMethodResponse.setCustomerId(payobj.getCustomersId());
        paymentMethodResponse.setMessage("The given customer is deleted");
        return paymentMethodResponse;
    }

    @Override
    public PaymentMethodResponse findCustomer(Integer customersId) {
        AddInstrumentEntity payobj = paymentMethodRepository.findByCustomersId(customersId);
        PaymentMethodResponse response = new PaymentMethodResponse();
        response.setMessage("The details of customer are:-");
        response.setCustomerId(payobj.getCustomersId());
        response.setFirstname(payobj.getFirstName());
        response.setLastname(payobj.getLastName());
        return response;
    }
}
