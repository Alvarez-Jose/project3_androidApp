package com.example.project3_androidapp.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListEntity {

    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("other_user_id")
    @Expose
    private Integer otherUserId;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserListEntity() {
    }

    /**
     *
     * @param ownerId
     * @param otherUserId
     */
    public UserListEntity(Integer ownerId, Integer otherUserId) {
        super();
        this.ownerId = ownerId;
        this.otherUserId = otherUserId;
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

}
