package com.sandbox.braintree.model;

public class PaymentMethodResponse {
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    private String code;
    private String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private String message;

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    private int customerId;
    private String firstname;

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    private String lastname;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
