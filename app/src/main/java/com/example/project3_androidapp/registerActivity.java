package com.example.project3_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.UserDao;
import com.example.project3_androidapp.db.UserEntity;
import com.example.project3_androidapp.util.Constants;
import com.example.project3_androidapp.util.StringAPIRequest;
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
        getUserDatabase();
        toMainButton = findViewById(R.id.registerUser);

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



//                StringAPIRequest request = new StringAPIRequest();
//                request.sendRequest();

                Intent intentMain = new Intent(registerActivity.this,
                        MainActivity.class);
                Toast.makeText(getApplicationContext(), "Created account successfully", Toast.LENGTH_LONG).show();
                System.out.println("TO MAIN");
                registerActivity.this.startActivity(intentMain);
            }

        };

        toMainButton.setOnClickListener(handler);
    }

    // get instance of database and return user DAO
    private void getUserDatabase() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        database = db.getInstance(this);
    }

    private void automaticLogin() {
        if (mSharedPrefs.getInt(Constants.USER_ID_KEY, -1) != -1) {
            Intent intentMain = new Intent(registerActivity.this,
                    MainActivity.class);
            System.out.println("AUTO LOGIN");
            registerActivity.this.startActivity(intentMain);
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