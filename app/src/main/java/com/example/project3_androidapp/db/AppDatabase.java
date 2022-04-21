package com.example.project3_androidapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {UserEntity.class, UserListEntity.class, CardEntity.class, CardListEntity.class, TransactionEntity.class, TransactionListEntity.class}, version = 1, exportSchema = false)
@TypeConverters(ArrayListConverter.class)

public abstract class AppDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "AppDatabase.db";
    public static final String USER_TABLE = "UserEntity";
    public static final String USER_LIST_TABLE = "user_list";
    public static final String TRANSACTION_TABLE = "transaction";
    public static final String TRANSACTION_LIST_TABLE = "transaction_list";
    public static final String CARD_TABLE = "card";
    public static final String CARD_LIST_TABLE = "card_list";

    private static AppDatabase instance;

    public abstract UserDao userDao();

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
