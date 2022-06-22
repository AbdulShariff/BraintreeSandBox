package com.sandbox.bdd.stepDefs;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.sandbox.bdd.TestContext.refundTransactionEndPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandbox.bdd.response.TransactionResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RefundTransactionStepdefs {
    BaseStepDef baseStepDef=new BaseStepDef();
    static TransactionResponse transactionResponse = null;
    String url = "http://localhost:8081/payment/v1/transaction/refund?transaction_id=7m1zpnsx";
    @Given("RefundTransaction request")
    public void refundtransaction_request() {
        refundTransactionEndPoint =  baseStepDef.getBraintreeInfo().getRefundTransactionEndPoint();
    }

    @Given("set {string} as the transaction id")
    public void set_as_the_transaction_id(String arg0) {
        baseStepDef.getBraintreeInfo().setVoidTransactionEndPoint(arg0);
    }

    @When("refundTransaction method is then called")
    public void refundtransactionMethodIsThenCalled() {
        RestTemplate restTemplate= new RestTemplate();
        ObjectMapper mapper= new ObjectMapper();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON );
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<TransactionResponse> transactions = restTemplate.exchange(url, HttpMethod.POST, entity, TransactionResponse.class);
        System.out.println(transactions.getBody());
        transactionResponse=transactions.getBody();
    }

    @Then("{string} returned as the response message")
    public void returnedAsTheResponseMessage(String arg0) {
        assertEquals(arg0, transactionResponse.getMessage());
    }
}
