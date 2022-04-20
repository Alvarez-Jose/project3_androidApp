
package com.example.project3_androidapp.db;

import java.util.HashMap;
import java.util.Map;

public class UserEntity {

    private Integer transactionId;
    private Integer amount;
    private String currency;
    private Integer isFinalized;
    private Integer sendingId;
    private Integer receivingId;
    private String description;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public UserEntity() {
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
    public UserEntity(Integer transactionId, Integer amount, String currency, Integer isFinalized, Integer sendingId, Integer receivingId, String description) {
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

    public UserEntity withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UserEntity withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UserEntity withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Integer getIsFinalized() {
        return isFinalized;
    }

    public void setIsFinalized(Integer isFinalized) {
        this.isFinalized = isFinalized;
    }

    public UserEntity withIsFinalized(Integer isFinalized) {
        this.isFinalized = isFinalized;
        return this;
    }

    public Integer getSendingId() {
        return sendingId;
    }

    public void setSendingId(Integer sendingId) {
        this.sendingId = sendingId;
    }

    public UserEntity withSendingId(Integer sendingId) {
        this.sendingId = sendingId;
        return this;
    }

    public Integer getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(Integer receivingId) {
        this.receivingId = receivingId;
    }

    public UserEntity withReceivingId(Integer receivingId) {
        this.receivingId = receivingId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity withDescription(String description) {
        this.description = description;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public UserEntity withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserEntity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("transactionId");
        sb.append('=');
        sb.append(((this.transactionId == null)?"<null>":this.transactionId));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("isFinalized");
        sb.append('=');
        sb.append(((this.isFinalized == null)?"<null>":this.isFinalized));
        sb.append(',');
        sb.append("sendingId");
        sb.append('=');
        sb.append(((this.sendingId == null)?"<null>":this.sendingId));
        sb.append(',');
        sb.append("receivingId");
        sb.append('=');
        sb.append(((this.receivingId == null)?"<null>":this.receivingId));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
