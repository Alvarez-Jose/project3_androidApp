package com.example.project3_androidapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CardDao {
    @Insert
    void insertCard(CardEntity... cards);

    @Update
    void updateCard(CardEntity... cards);

    @Delete
    void delete(CardEntity... card);

    @Query("SELECT * FROM " + AppDatabase.CARD_TABLE + " WHERE cardId = :cardId")
    CardEntity getCardById(int cardId);

    @Query("SELECT * FROM " + AppDatabase.CARD_TABLE + " ORDER BY cardId DESC")
    List<CardEntity> getAllCards();

    @Query("DELETE FROM " + AppDatabase.CARD_TABLE + " WHERE cardId = :cardId")
    void deleteCard(int cardId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.CARD_TABLE + " WHERE cardId = :cardId)")
    Boolean cardExists(int cardId);
}
