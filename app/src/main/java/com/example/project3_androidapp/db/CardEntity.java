package com.example.project3_androidapp.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardEntity {

    @SerializedName("card_id")
    @Expose
    private Integer cardId;
    @SerializedName("card_num")
    @Expose
    private Integer cardNum;
    @SerializedName("expiration")
    @Expose
    private Integer expiration;
    @SerializedName("cvv")
    @Expose
    private Integer cvv;
    @SerializedName("holder_name")
    @Expose
    private String holderName;
    @SerializedName("zip")
    @Expose
    private Integer zip;
    @SerializedName("card_nickname")
    @Expose
    private String cardNickname;

    /**
     * No args constructor for use in serialization
     *
     */
    public CardEntity() {
    }

    /**
     *
     * @param zip
     * @param cardNum
     * @param cvv
     * @param holderName
     * @param cardNickname
     * @param cardId
     * @param expiration
     */
    public CardEntity(Integer cardId, Integer cardNum, Integer expiration, Integer cvv, String holderName, Integer zip, String cardNickname) {
        super();
        this.cardId = cardId;
        this.cardNum = cardNum;
        this.expiration = expiration;
        this.cvv = cvv;
        this.holderName = holderName;
        this.zip = zip;
        this.cardNickname = cardNickname;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public CardEntity withCardId(Integer cardId) {
        this.cardId = cardId;
        return this;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public CardEntity withCardNum(Integer cardNum) {
        this.cardNum = cardNum;
        return this;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public CardEntity withExpiration(Integer expiration) {
        this.expiration = expiration;
        return this;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public CardEntity withCvv(Integer cvv) {
        this.cvv = cvv;
        return this;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public CardEntity withHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public CardEntity withZip(Integer zip) {
        this.zip = zip;
        return this;
    }

    public String getCardNickname() {
        return cardNickname;
    }

    public void setCardNickname(String cardNickname) {
        this.cardNickname = cardNickname;
    }

    public CardEntity withCardNickname(String cardNickname) {
        this.cardNickname = cardNickname;
        return this;
    }

}