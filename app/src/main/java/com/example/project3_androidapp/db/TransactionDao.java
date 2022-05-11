package com.example.project3_androidapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insertTransaction(TransactionEntity... transactions);

    @Update
    void updateTransaction(TransactionEntity... transactions);

    @Delete
    void delete(TransactionEntity... transact);

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE transactionId = :transactionId")
    List<TransactionEntity> getTransactionById(int transactionId);

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " ORDER BY transactionId DESC")
    List<TransactionEntity> getAllTransactions();

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE receivingId = :id  OR  sendingId = :id")
    List<TransactionEntity> getAllTransactionsById(int id);

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE receivingId = (Select userId FROM "+AppDatabase.USER_TABLE+" where username = :name)")
    List<TransactionEntity> getAllTransactionsByName(String name);

    @Query("DELETE FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE transactionId = :transactionId")
    void deleteTransaction(int transactionId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE transactionId = :transactionId)")
    Boolean transactionExists(int transactionId);

    @Query("SELECT MAX(transactionId) FROM " + AppDatabase.TRANSACTION_TABLE)
    int getHighestId();
}
