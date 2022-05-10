package com.example.project3_androidapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TransactionEntity {

    @PrimaryKey
    @SerializedName("transaction_id")
    @Expose
    private Integer transactionId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("is_finalized")
    @Expose
    private Integer isFinalized;
    @SerializedName("sending_id")
    @Expose
    private Integer sendingId;
    @SerializedName("receiving_id")
    @Expose
    private Integer receivingId;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     */
    public TransactionEntity() {
    }

    /**
     * @param amount
     * @param sendingId
     * @param description
     * @param currency
     * @param transactionId
     * @param isFinalized
     * @param receivingId
     */
    public TransactionEntity(Integer transactionId, Integer amount, String currency, Integer isFinalized, Integer sendingId, Integer receivingId, String description) {
        super();
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.isFinalized = isFinalized;
        this.sendingId = sendingId;
        this.receivingId = receivingId;
        this.description = description;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionEntity withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public TransactionEntity withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TransactionEntity withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Integer getIsFinalized() {
        return isFinalized;
    }

    public void setIsFinalized(Integer isFinalized) {
        this.isFinalized = isFinalized;
    }

    public TransactionEntity withIsFinalized(Integer isFinalized) {
        this.isFinalized = isFinalized;
        return this;
    }

    public Integer getSendingId() {
        return sendingId;
    }

    public void setSendingId(Integer sendingId) {
        this.sendingId = sendingId;
    }

    public TransactionEntity withSendingId(Integer sendingId) {
        this.sendingId = sendingId;
        return this;
    }

    public Integer getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(Integer receivingId) {
        this.receivingId = receivingId;
    }

    public TransactionEntity withReceivingId(Integer receivingId) {
        this.receivingId = receivingId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionEntity withDescription(String description) {
        this.description = description;
        return this;
    }

}