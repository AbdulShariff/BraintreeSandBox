package com.sandbox.bdd.stepDefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static com.sandbox.bdd.TestContext.addCustomerEndPoint;
import static com.sandbox.bdd.TestContext.paymentEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandbox.bdd.request.PaymentEntity;
import com.sandbox.bdd.response.PaymentMethodResponse;
import com.sandbox.bdd.stepDefs.config.JsonTestHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;

public class AddCustomerStepdefs {
    BaseStepDef baseStepDef=new BaseStepDef();
    static PaymentMethodResponse paymentsResponse = null;
    private static final String ADD_CUSTOMER_REQUEST_JSON = "/Users/khanabdulshariff/Downloads/BraintreeSandBox/src/test/resources/request/AddCustomer.json";
     String url = "http://localhost:8081/payment/instrument/v1/customer";
    @Given("an add customer request")
    public void an_add_customer_request() {
        addCustomerEndPoint =  baseStepDef.getBraintreeInfo().getAddCustomerEndpoint();
    }
    @Given("set {string} as first name")
    public void set_as_first_name(String arg0) {
        baseStepDef.getBraintreeInfo().setAddCustomerEndpoint(arg0);
    }
    @Given("set {string} as last name")
    public void set_as_last_name(String arg0) {
        baseStepDef.getBraintreeInfo().setAddCustomerEndpoint(arg0);
    }
    @Given("set {string} as phone")
    public void set_as_phone(String arg0) {
        baseStepDef.getBraintreeInfo().setAddCustomerEndpoint(arg0);
    }
    @Given("set {string} as email")
    public void set_as_email(String arg0) {
        baseStepDef.getBraintreeInfo().setAddCustomerEndpoint(arg0);
    }
    @When("instrument onboard api is called")
    public void instrument_onboard_api_is_called() throws JsonProcessingException {
        ObjectMapper mapper= new ObjectMapper();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType. APPLICATION_JSON);
        RestTemplate restTemplate= new RestTemplate();
        try {
            paymentEntity = new JsonTestHelper().getRequest(
                    ADD_CUSTOMER_REQUEST_JSON, PaymentEntity.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> entity=new HttpEntity<>(mapper.writeValueAsString(paymentEntity),headers);
        ResponseEntity<PaymentMethodResponse> payments = restTemplate.exchange(url, HttpMethod.POST, entity, PaymentMethodResponse.class);
        System.out.println(payments.getBody());
        paymentsResponse=payments.getBody();
    }
    @Then("a non-empty customer_id should be generated")
    public void aNonEmptyCustomer_idShouldBeGenerated() {
        paymentsResponse.getCustomerId();
    }
    @And("{string} returned as  response message must match")
    public void returned_as_response_message_must_match(String arg0) {
        assertEquals(arg0, paymentsResponse.getMessage());
    }
    @And("{string} returned as response code")
    public void returnedAsResponseCode(String arg0) {
        assertEquals(arg0,String.valueOf(paymentsResponse.getCode()));
    }
    @And("{string} returned as first name")
    public void returnedAsFirstName(String arg0) {
        assertEquals(arg0, paymentsResponse.getFirstname());
    }
    @And("{string} returned as last name")
    public void returnedAsLastName(String arg0) {
        assertEquals(arg0, paymentsResponse.getLastname());
    }
}
