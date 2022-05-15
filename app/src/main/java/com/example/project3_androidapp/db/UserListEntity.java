package com.example.project3_androidapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class UserListEntity {

    @PrimaryKey
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("other_user_id")
    @Expose
    private Integer otherUserId;
    @SerializedName("is_accepted")
    @Expose
    private Integer isAccepted;

    /**
     * No args constructor for use in serialization
     */
    public UserListEntity() {
    }

    /**
     * @param ownerId
     * @param isAccepted
     * @param otherUserId
     */
    public UserListEntity(Integer ownerId, Integer otherUserId, Integer isAccepted) {
        super();
        this.ownerId = ownerId;
        this.otherUserId = otherUserId;
        this.isAccepted = isAccepted;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public UserListEntity withOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Integer getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(Integer otherUserId) {
        this.otherUserId = otherUserId;
    }

    public UserListEntity withOtherUserId(Integer otherUserId) {
        this.otherUserId = otherUserId;
        return this;
    }

    public Integer getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Integer isAccepted) {
        this.isAccepted = isAccepted;
    }

    public UserListEntity withIsAccepted(Integer isAccepted) {
        this.isAccepted = isAccepted;
        return this;
    }

}