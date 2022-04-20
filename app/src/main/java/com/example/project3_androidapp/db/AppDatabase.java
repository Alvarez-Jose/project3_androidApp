package com.example.project3_androidapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
@TypeConverters(ArrayListConverter.class)

public abstract class AppDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "AppDatabase.db";
    public static final String USER_TABLE = "userTable";

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
