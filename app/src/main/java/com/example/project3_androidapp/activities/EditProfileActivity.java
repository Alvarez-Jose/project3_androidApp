package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;

public class EditProfileActivity extends AppCompatActivity {

    private Button submitButton, backButton;
    private EditText newName, newPass, newBank, added;
    private String nameText, passText;
    private Double bankAmt, addedValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

//        submitButton = findViewById(R.id);
//        backButton = findViewById(R.id);
//        newName = findViewById(R.id);
//        newPass = findViewById(R.id);
//        newBank = findViewById(R.id);

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

//            if(v == addFundsButton){
//                addFunds(added);
//            }

//        };

//        backButton.setOnClickListener(handler);
//        submitButton.setOnClickListener(handler);
    }

    private void addFunds(double amount) {

    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(EditProfileActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}