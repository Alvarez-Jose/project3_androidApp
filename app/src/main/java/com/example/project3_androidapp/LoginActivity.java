package com.example.project3_androidapp;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.util.Constants;
import com.google.gson.JsonArray;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView user;
    Button toMainButton, loginButton, checkLogin;
    AppDatabase database;
    EditText username, password;
    String enteredUsername, enteredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // username = findViewById(R.id.fullname);
        // password = findViewById(R.id.password);
        // checkLogin = findViewById(R.id.loginButton);
        toMainButton = findViewById(R.id.loginToMain);

        View.OnClickListener handler = v -> {

//            if (v == loginButton) {
//                enteredUsername = username.getText().toString();
//                enteredPassword = password.getText().toString();
//
//                String url = URL_BASE + "/retrieve_user/?user=&pass";
//
//                RequestQueue queue = Volley.newRequestQueue(this);
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
//                    Toast.makeText(getApplicationContext(), "Logged in!", Toast.LENGTH_LONG).show();
//                    //Login success path
//                }, err -> {
//                    Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG).show();
//                    switchToMain();
//                });
//                queue.add(stringRequest);
//            }

            if(v == toMainButton){
                switchToMain();
            }

        };

        toMainButton.setOnClickListener(handler);
        //loginButton.setOnClickListener(handler);
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}