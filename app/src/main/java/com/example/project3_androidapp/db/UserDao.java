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

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE user_id = :userId")
    UserEntity getUserById(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " ORDER BY user_id DESC")
    List<UserEntity> getAllUsers();

    @Query("DELETE FROM " + AppDatabase.USER_TABLE + " WHERE user_id = :userId")
    void deleteUser(int userId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE user_id = :userId)")
    Boolean userExists(int userId);
}
