package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.util.Constants;

public class AddCardsActivity extends AppCompatActivity {

    private EditText cardNumber, cvv, expMonth, expYear, zip;
    private int numberValue, cvvValue, monthValue, yearValue, zipValue;
    private int idValue;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);
//        String url = URL_BASE + "/add_card/?user=&pass";
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