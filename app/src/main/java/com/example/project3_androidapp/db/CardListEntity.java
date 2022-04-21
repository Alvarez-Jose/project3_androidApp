package com.example.project3_androidapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CardListEntity {

    @PrimaryKey
    @SerializedName("card_list_id")
    @Expose
    private Integer cardListId;
    @SerializedName("card_id")
    @Expose
    private Integer cardId;

    /**
     * No args constructor for use in serialization
     *
     */
    public CardListEntity() {
    }

    /**
     *
     * @param cardId
     * @param cardListId
     */
    public CardListEntity(Integer cardListId, Integer cardId) {
        super();
        this.cardListId = cardListId;
        this.cardId = cardId;
    }

    public Integer getCardListId() {
        return cardListId;
    }

    public void setCardListId(Integer cardListId) {
        this.cardListId = cardListId;
    }

    public CardListEntity withCardListId(Integer cardListId) {
        this.cardListId = cardListId;
        return this;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public CardListEntity withCardId(Integer cardId) {
        this.cardId = cardId;
        return this;
    }

}