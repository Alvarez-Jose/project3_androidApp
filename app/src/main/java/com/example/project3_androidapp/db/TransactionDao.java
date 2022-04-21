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

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE transaction_id = :transactionId")
    TransactionEntity getTransactionById(int transactionId);

    @Query("SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " ORDER BY transaction_id DESC")
    List<TransactionEntity> getAllTransactions();

    @Query("DELETE FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE transaction_id = :transactionId")
    void deleteTransaction(int transactionId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.TRANSACTION_TABLE + " WHERE transaction_id = :transactionId)")
    Boolean transactionExists(int transactionId);
}
