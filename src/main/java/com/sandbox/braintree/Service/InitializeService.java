package com.sandbox.braintree.Service;

import com.sandbox.braintree.model.AddInstrumentEntity;
import com.sandbox.braintree.model.AddInstrumentRequest;
import com.sandbox.braintree.model.PaymentMethodResponse;

public interface InitializeService {
    PaymentMethodResponse onboard(AddInstrumentRequest addInstrumentRequest, String nonce, Integer id);

    PaymentMethodResponse addCustomer(AddInstrumentRequest addInstrumentRequest);

    PaymentMethodResponse deleteCustomer(Integer id);

    PaymentMethodResponse findCustomer(Integer customersId);
}
