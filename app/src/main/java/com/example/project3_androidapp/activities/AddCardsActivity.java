package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;

public class AddCardsActivity extends AppCompatActivity {

    private EditText cardNumber, cvv, expMonth, expYear, zip;
    private int numberValue, cvvValue, monthValue, yearValue, zipValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);

//        String url = URL_BASE + "/retrieve_user/?user=&pass";
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
//            Toast.makeText(getApplicationContext(), "Logged in!", Toast.LENGTH_LONG).show();
//        }, err -> {
//            Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG).show();
//            switchToMain();
//        });
//        queue.add(stringRequest);

//        View.OnClickListener handler = v -> {

//            if (v == toEditButton) {
//                switchToMain();
//            }

//            if(v == backButton){
//                switchToMain();
//            }

//        };

//        backButton.setOnClickListener(handler);
//        submitButton.setOnClickListener(handler);
    }
    private void switchToMain() {
        Intent switchActivityIntent = new Intent(AddCardsActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}