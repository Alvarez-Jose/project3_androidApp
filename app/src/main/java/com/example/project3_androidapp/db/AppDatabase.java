package com.example.project3_androidapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {UserEntity.class, UserListEntity.class, CardEntity.class, CardListEntity.class, TransactionEntity.class, TransactionListEntity.class}, version = 3, exportSchema = false)
@TypeConverters(ArrayListConverter.class)

public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "AppDatabase.db";
    public static final String USER_TABLE = "UserEntity";
    public static final String USER_LIST_TABLE = "UserListEntity";
    public static final String TRANSACTION_TABLE = "TransactionEntity";
    public static final String TRANSACTION_LIST_TABLE = "TransactionListEntity";
    public static final String CARD_TABLE = "CardEntity";
    public static final String CARD_LIST_TABLE = "CardListEntity";

    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract UserListDao userListDao();

    public abstract CardDao cardDao();

    public abstract CardListDao cardListDao();

    public abstract TransactionDao transactionDao();

    public abstract TransactionListDao transactionListDao();

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
