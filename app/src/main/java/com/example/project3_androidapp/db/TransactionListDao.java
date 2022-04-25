package com.example.project3_androidapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionListDao {
    @Insert
    void insertTransactionList(TransactionListEntity... transactionLists);

    @Update
    void updateTransactionList(TransactionListEntity... transactionLists);

    @Delete
    void delete(TransactionListEntity... transactionList);

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_LIST_TABLE + " WHERE transactionListId = :transactionListId")
    TransactionListEntity getTransactionListById(int transactionListId);

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_LIST_TABLE + " ORDER BY transactionListId DESC")
    List<TransactionListEntity> getAllTransactionLists();

    @Query("DELETE FROM " + AppDatabase.TRANSACTION_LIST_TABLE + " WHERE transactionListId = :transactionListId")
    void deleteTransactionList(int transactionListId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.TRANSACTION_LIST_TABLE + " WHERE transactionListId = :transactionListId)")
    Boolean transactionListExists(int transactionListId);
}
