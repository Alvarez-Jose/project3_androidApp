package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.UserDao;
import com.example.project3_androidapp.db.UserEntity;
import com.example.project3_androidapp.util.Constants;

public class RegisterActivity extends AppCompatActivity {

    private Button toMainButton;
    private EditText userName, userEmail, userPassword, userRepeatPass;
    private String userNameText = "", userEmailText = "", userPasswordText = "", userRepeatPassText = "", existingUsers = "";
    private int userId = 0, max = -1;
    private AppDatabase database;
    private UserDao userDao;

    SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mSharedPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (mSharedPrefs.getInt(Constants.USER_ID_KEY, -1) != -1)
            logout(mSharedPrefs);

        getUserDatabase();
        userDao = database.userDao();

        toMainButton = findViewById(R.id.registerUser);
        userName = findViewById(R.id.fullname);
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        // TODO add user repeated password for registering

        mSharedPrefs = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE);


        View.OnClickListener handler = v -> {

            if (v == toMainButton) {
                // register items and return to main activity to force login
                userNameText = userName.getText().toString();
                userEmailText = userEmail.getText().toString();
                userPasswordText = userPassword.getText().toString();
//                getNewId(); // will probably be useless but keeping for now.

                RequestQueue queue = Volley.newRequestQueue(this); // used to access api
                String url = URL_BASE + "/retrieve_users_and";

                Intent intentRepeat = new Intent(RegisterActivity.this,
                        RegisterActivity.class); // if the user makes an error on the form.

                StringRequest usersRequest = new StringRequest(Request.Method.GET, url, response -> {
                    // maybe a confirmation message
                    existingUsers = response.replaceAll("&#34;", "\"");
                    System.out.println(existingUsers);
                }, e -> {
                    Toast.makeText(getApplicationContext(), "Error connecting to database", Toast.LENGTH_LONG).show();
                });

                queue.add(usersRequest);
                // need to make sure the form is filled out properly
                if (userNameText.contains( ("\"username\":" + existingUsers + "\"") )|| userNameText.isEmpty() || userPasswordText.isEmpty()) {
                    if (userNameText.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Username is empty.", Toast.LENGTH_LONG).show();
                    } else if (userPasswordText.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Password is empty.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Username taken.", Toast.LENGTH_LONG).show();
                    }
                    RegisterActivity.this.startActivity(intentRepeat);
                } else { // if the form is filled correctly

                    usersRequest = new StringRequest(Request.Method.GET, url, response -> {
                        // maybe a confirmation message
                        existingUsers = response;
                    }, e -> {
                        Toast.makeText(getApplicationContext(), "Error connecting to database", Toast.LENGTH_LONG).show();
                    });

                    Intent intentMain = new Intent(RegisterActivity.this,
                            MainActivity.class);

                    url = URL_BASE + "/create_user/?username=" + userNameText + "&password=" + userPasswordText + "&admin=0&cardListId=" + userDao.getHighestId() + "&userListId=" + userDao.getHighestId() + "&transactionListId=" + userDao.getHighestId() + "";
                    //?username=new&password=new&admin=0&cardListId=0&userListId=0&transactionListId=0

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                        // maybe a confirmation message
                        Toast.makeText(getApplicationContext(), "Success! account added to database", Toast.LENGTH_LONG).show();
                    }, e -> Toast.makeText(getApplicationContext(), "Error sending account to database", Toast.LENGTH_LONG).show());

                    queue.add(stringRequest);

                    // add the user to the DAO only after put into database
//                UserEntity newUser = new UserEntity(userId, userNameText, userPasswordText, 1, /*lists are the same as user id*/ userId, userId, 0.00, userId);
//                database.userDao().insertUser(newUser);

                    Toast.makeText(getApplicationContext(), "Created account successfully", Toast.LENGTH_LONG).show();
                    System.out.println("TO MAIN");
                    RegisterActivity.this.startActivity(intentMain);
                }
            }

        };

        toMainButton.setOnClickListener(handler);
    }

    private void getExistingUsers() {

    }

    // get instance of database and return user DAO
    private void getUserDatabase() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        database = db.getInstance(this);
    }


    private void getNewId() {
        // getting the user ID since it needs to be inserted manually.
        for (UserEntity u : database.userDao().getAllUsers()) {
            max = u.getUserId();
            if (max > userId)
                userId = ++max;
        }
    }

    public void logout(SharedPreferences sp) {
        if (sp.getInt(Constants.USER_ID_KEY, -1) != -1) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(Constants.USER_ID_KEY, -1);
            editor.apply();
        }
        Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}