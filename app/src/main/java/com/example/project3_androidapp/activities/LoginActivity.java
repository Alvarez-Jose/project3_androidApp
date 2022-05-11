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
import com.example.project3_androidapp.util.Constants;

public class LoginActivity extends AppCompatActivity {

    public static int idValue = -1;

    private Button toMainButton, loginButton;
    private AppDatabase database;
    private EditText username, password;
    private String enteredUsername, enteredPassword;
    private boolean isSuccessful;

    SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSharedPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        automaticLogin();

        idValue = -1;
        isSuccessful = false;


        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.submitLogin);
        toMainButton = findViewById(R.id.loginToMain);

        View.OnClickListener handler = v -> {

            if (v == loginButton) {
                enteredUsername = username.getText().toString();
                enteredPassword = password.getText().toString();


                String url = URL_BASE + "/retrieve_user_p/?u=" + enteredUsername + "&p=" + enteredPassword;
                // use the api in order to find the account on the database.

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                    //Login success path
                    idValue = Integer.parseInt(response);
//                    System.out.println(response);
//                    System.out.println(idValue);
                    if (idValue != -1) {
                        // login success saves the user to persistent login
                        SharedPreferences.Editor editor = mSharedPrefs.edit();
                        editor.putInt(Constants.USER_ID_KEY, idValue);
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                        switchToTransactions();
                    }
                }, err -> {
                    Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG).show();
                    refreshPage();
                });

                queue.add(stringRequest);
            }

            if (v == toMainButton) {
                switchToMain();
            }

        };

        toMainButton.setOnClickListener(handler);
        loginButton.setOnClickListener(handler);
    }

    private void refreshPage() {
        Intent switchActivityIntent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToTransactions() {
        Intent switchActivityIntent = new Intent(LoginActivity.this, LoadingActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void automaticLogin() {
        if (mSharedPrefs.getInt(Constants.USER_ID_KEY, -1) != -1) {
            Intent intentMain = new Intent(LoginActivity.this,
                    LoadingActivity.class);
            System.out.println("AUTO LOGIN");
            LoginActivity.this.startActivity(intentMain);
        }
    }
}