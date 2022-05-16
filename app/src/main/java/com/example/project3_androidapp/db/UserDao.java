package com.example.project3_androidapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserEntity... users);

    @Update
    void updateUser(UserEntity... users);

    @Delete
    void delete(UserEntity... user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE userId = :userId")
    UserEntity getUserById(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " ORDER BY userId DESC")
    List<UserEntity> getAllUsers();

    @Query("DELETE FROM " + AppDatabase.USER_TABLE + " WHERE userId = :userId")
    void deleteUser(int userId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE userId = :userId)")
    Boolean userExists(int userId);

    @Query("SELECT MAX(userId) FROM " + AppDatabase.USER_TABLE)
    int getHighestId();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username LIKE :user")
    List<UserEntity> getUsersByName(String user);

}
