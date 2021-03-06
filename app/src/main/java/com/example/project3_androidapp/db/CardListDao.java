package com.example.project3_androidapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CardListDao {
    @Insert
    void insertCardList(CardListEntity... cardLists);

    @Update
    void updateCardList(CardListEntity... cardLists);

    @Delete
    void delete(CardListEntity... cardList);

    @Query("SELECT * FROM " + AppDatabase.CARD_LIST_TABLE + " WHERE cardListId = :cardListId")
    CardListEntity getCardListById(int cardListId);

    @Query("SELECT * FROM " + AppDatabase.CARD_LIST_TABLE + " ORDER BY cardListId DESC")
    List<CardListEntity> getAllCardLists();

    @Query("DELETE FROM " + AppDatabase.CARD_LIST_TABLE + " WHERE cardListId = :cardListId")
    void deleteCardList(int cardListId);

    @Query("SELECT EXISTS(SELECT * FROM " + AppDatabase.CARD_LIST_TABLE + " WHERE cardListId = :cardListId)")
    Boolean cardListExists(int cardListId);
}
