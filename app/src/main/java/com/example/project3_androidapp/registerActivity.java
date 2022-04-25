package com.example.project3_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.UserEntity;
import com.example.project3_androidapp.util.Constants;
import com.google.firebase.firestore.auth.User;

public class registerActivity extends AppCompatActivity {

    private Button toMainButton;
    private EditText userName, userEmail, userPassword, userRepeatPass;
    private String userNameText, userEmailText, userPasswordText, userRepeatPassText;
    private int userId = 0, max;
    private AppDatabase database;

    SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        toMainButton = (Button) findViewById(R.id.registerUser);
        database = AppDatabase.getInstance(getApplicationContext());

        userName = findViewById(R.id.fullname);
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        // TODO add user repeated password for registering

        mSharedPrefs = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE);

        automaticLogin();

        View.OnClickListener handler = v -> {

            if (v == toMainButton) {
                // register items and return to main activity to force login
                userNameText = userName.getText().toString();
                userEmailText = userEmail.getText().toString();
                userPasswordText = userPassword.getText().toString();

                getNewId();

                UserEntity newUser = new UserEntity(userId, userNameText, userPasswordText, 1, /*lists are the same as user id*/ userId, userId, 0.00, userId);
                database.userDao().insertUser(newUser);


                Intent intentMain = new Intent(registerActivity.this,
                        MainActivity.class);
                Toast.makeText(getApplicationContext(), "Created account successfully", Toast.LENGTH_LONG).show();
                registerActivity.this.startActivity(intentMain);
                System.out.println("TO MAIN");
            }

        };

        toMainButton.setOnClickListener(handler);
    }
    private void automaticLogin() {
        if (mSharedPrefs.getInt(Constants.USER_ID_KEY, -1) != -1) {
            Intent intentMain = new Intent(registerActivity.this,
                    MainActivity.class);
            registerActivity.this.startActivity(intentMain);
            System.out.println("AUTO LOGIN");
        }
    }

    private void getNewId(){
        // getting the user ID since it needs to be inserted manually.
        for(UserEntity u: database.userDao().getAllUsers()) {
            max = u.getUserId();
            if (max > userId)
                userId = ++max;
        }
    }
}