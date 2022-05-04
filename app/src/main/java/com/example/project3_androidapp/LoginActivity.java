package com.example.project3_androidapp;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.activities.TransactionsActivity;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.util.Constants;
import com.google.gson.JsonArray;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextView user;
    private Button toMainButton, loginButton, checkLogin;
    private AppDatabase database;
    private EditText username, password;
    private String enteredUsername, enteredPassword;
    private boolean isSuccessful;
    private int idValue;

    SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        automaticLogin();

        isSuccessful = false;

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextTextPassword);
        checkLogin = findViewById(R.id.submitLogin);
        toMainButton = findViewById(R.id.loginToMain);

        View.OnClickListener handler = v -> {

            if (v == loginButton) {
                enteredUsername = username.getText().toString();
                enteredPassword = password.getText().toString();

                String url = URL_BASE + "/login_account/?u=" + enteredUsername + "&p=" + enteredPassword;
                // use the api in order to find the account on the database.

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                    //Login success path
                    if(response.indexOf(":") != -1) {
                        idValue = Integer.parseInt(response.substring(response.indexOf("id:"),response.indexOf("}")));

//                        isSuccessful = true;
                    }
                }, err -> {
                    Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG).show();
                    isSuccessful = false;
                });

                queue.add(stringRequest);

                if(isSuccessful){
                    // login success saves the user to persistent login
                    SharedPreferences.Editor editor = mSharedPrefs.edit();
                    editor.putInt(Constants.USER_ID_KEY, idValue);
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                    switchToTransactions();
                } else {
                    refreshPage();
                }
            }

            if(v == toMainButton){
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
        Intent switchActivityIntent = new Intent(LoginActivity.this, TransactionsActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void automaticLogin() {
        if (mSharedPrefs.getInt(Constants.USER_ID_KEY, -1) != -1) {
            Intent intentMain = new Intent(LoginActivity.this,
                    TransactionsActivity.class);
            System.out.println("AUTO LOGIN");
            LoginActivity.this.startActivity(intentMain);
        }
    }
}