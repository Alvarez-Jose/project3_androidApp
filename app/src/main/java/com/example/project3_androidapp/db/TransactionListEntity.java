package com.example.project3_androidapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TransactionListEntity {

    @PrimaryKey
    @SerializedName("transaction_list_id")
    @Expose
    private Integer transactionListId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("transaction_id")
    @Expose
    private Integer transactionId;

    /**
     * No args constructor for use in serialization
     *
     */
    public TransactionListEntity() {
    }

    /**
     *
     * @param transactionListId
     * @param userId
     * @param transactionId
     */
    public TransactionListEntity(Integer transactionListId, Integer userId, Integer transactionId) {
        super();
        this.transactionListId = transactionListId;
        this.userId = userId;
        this.transactionId = transactionId;
    }

    public Integer getTransactionListId() {
        return transactionListId;
    }

    public void setTransactionListId(Integer transactionListId) {
        this.transactionListId = transactionListId;
    }

    public TransactionListEntity withTransactionListId(Integer transactionListId) {
        this.transactionListId = transactionListId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TransactionListEntity withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionListEntity withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

}
