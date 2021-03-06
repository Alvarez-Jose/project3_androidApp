package com.example.project3_androidapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserListDao {
    @Insert
    void insertUserList(UserListEntity... users);

    @Update
    void updateUserList(UserListEntity... users);

    @Delete
    void delete(UserListEntity... user);

    @Query("SELECT * FROM " + AppDatabase.USER_LIST_TABLE + " WHERE ownerId = :userListId")
    UserListEntity getUserListById(int userListId);

    @Query("SELECT * FROM " + AppDatabase.USER_LIST_TABLE + " ORDER BY ownerId DESC")
    List<UserListEntity> getAllUserLists();

    @Query("DELETE FROM " + AppDatabase.USER_LIST_TABLE + " WHERE ownerId = :userListId")
    void deleteUserList(int userListId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.USER_LIST_TABLE + " WHERE ownerId = :userListId)")
    Boolean userListExists(int userListId);
}
