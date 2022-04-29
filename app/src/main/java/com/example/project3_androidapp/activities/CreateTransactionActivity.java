package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;

public class CreateTransactionActivity extends AppCompatActivity {

    private Button submitButton, backButton;
    private EditText transactionAmount, userId;
    private int idValue;
    private double amountValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

//        submitButton = findViewById(R.id);
//        backButton = findViewById(R.id);
//        transactionAmount = findViewById(R.id);
//        userId = findViewById(R.id);

//        View.OnClickListener handler = v -> {

//            if (v == submitButton) {
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

//            if(v == backButton){
//                switchToMain();
//            }

//        };

//        backButton.setOnClickListener(handler);
//        submitButton.setOnClickListener(handler);
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(CreateTransactionActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}