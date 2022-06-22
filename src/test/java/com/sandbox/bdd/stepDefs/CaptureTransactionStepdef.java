package com.sandbox.bdd.stepDefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static com.sandbox.bdd.TestContext.captureTransactionEndpoint;
import com.sandbox.bdd.response.TransactionResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CaptureTransactionStepdef {
    BaseStepDef baseStepDef = new BaseStepDef();
    static TransactionResponse transactionResponse = null;
    String url = "http://localhost:8081/payment/v1/transaction/capture?transaction_id=dx0qhqmh";

    @Given("a capture transaction request")
    public void aCaptureTransactionRequest() {
         captureTransactionEndpoint = baseStepDef.getBraintreeInfo().getCaptureTransactionEndpoint();
    }

    @When("set {string} as transaction_id")
    public void setAsTransaction_id(String arg0) {
        baseStepDef.getBraintreeInfo().setCaptureTransactionEndpoint(arg0);
    }

    @When("capture api is called")
    public void captureApiIsCalled() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<TransactionResponse> capture = restTemplate.exchange(url, HttpMethod.POST, entity, TransactionResponse.class);
        System.out.println(capture.getBody());
        transactionResponse = capture.getBody();
    }

    @Then("{string} returned as  response transaction_message must match")
    public void returnedAsResponseTransaction_messageMustMatch(String arg0) {
        assertEquals(arg0, transactionResponse.getMessage());
    }

    @Then("{string} returned as  response status must match")
    public void returnedAsResponseStatusMustMatch(String arg0) {
        assertEquals(arg0, transactionResponse.getTransactionStatus());
    }
}
