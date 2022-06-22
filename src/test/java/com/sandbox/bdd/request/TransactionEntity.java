package com.sandbox.bdd.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@Entity(name = "Transaction_details")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serialno")
    private int serialNo;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column
    @JsonProperty("amount")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal amount;

    @Column
    @JsonProperty("token_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tokenId;

    @Column
    @JsonProperty("status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @Column
    @JsonProperty("payment_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String paymentType;

    @Column(length = 200)
    @JsonProperty("transaction_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transactionDate;

    @Column(name = "transaction_id", nullable = true)
    private String transactionId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }
}
