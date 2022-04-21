package com.example.project3_androidapp.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserEntity {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("admin")
    @Expose
    private Integer admin;
    @SerializedName("card_list_id")
    @Expose
    private Integer cardListId;
    @SerializedName("user_list_id")
    @Expose
    private Integer userListId;
    @SerializedName("bank")
    @Expose
    private Integer bank;
    @SerializedName("transaction_list_id")
    @Expose
    private Integer transactionListId;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserEntity() {
    }

    /**
     *
     * @param password
     * @param bank
     * @param transactionListId
     * @param admin
     * @param userId
     * @param userListId
     * @param cardListId
     * @param username
     */
    public UserEntity(Integer userId, String username, String password, Integer admin, Integer cardListId, Integer userListId, Integer bank, Integer transactionListId) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.cardListId = cardListId;
        this.userListId = userListId;
        this.bank = bank;
        this.transactionListId = transactionListId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserEntity withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserEntity withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity withPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public UserEntity withAdmin(Integer admin) {
        this.admin = admin;
        return this;
    }

    public Integer getCardListId() {
        return cardListId;
    }

    public void setCardListId(Integer cardListId) {
        this.cardListId = cardListId;
    }

    public UserEntity withCardListId(Integer cardListId) {
        this.cardListId = cardListId;
        return this;
    }

    public Integer getUserListId() {
        return userListId;
    }

    public void setUserListId(Integer userListId) {
        this.userListId = userListId;
    }

    public UserEntity withUserListId(Integer userListId) {
        this.userListId = userListId;
        return this;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public UserEntity withBank(Integer bank) {
        this.bank = bank;
        return this;
    }

    public Integer getTransactionListId() {
        return transactionListId;
    }

    public void setTransactionListId(Integer transactionListId) {
        this.transactionListId = transactionListId;
    }

    public UserEntity withTransactionListId(Integer transactionListId) {
        this.transactionListId = transactionListId;
        return this;
    }

}