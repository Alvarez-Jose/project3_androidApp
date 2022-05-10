package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

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

public class AddCardsActivity extends AppCompatActivity {

    private EditText cardNumber, cvv, expMonth, expYear, zip;
    private int numberValue, cvvValue, monthValue, yearValue, zipValue;
    private int idValue;
    private Button backButton, submitButton;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);

        checkLogin();

        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submitLogin);

        // get user's shared preferences
//        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);
        String url = URL_BASE + "/"; // TODO change url to add necessary fields

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Toast.makeText(getApplicationContext(), "succ", Toast.LENGTH_LONG).show();
        }, err -> {
            Toast.makeText(getApplicationContext(), "err.", Toast.LENGTH_LONG).show();
            switchToMain();
        });
        queue.add(stringRequest);

        View.OnClickListener handler = v -> {

            if (v == submitButton) {
                switchToMain();
            }

            if(v == backButton){
                switchToMain();
            }

        };

        backButton.setOnClickListener(handler);
        submitButton.setOnClickListener(handler);
    }

    private void checkLogin() {
        if(idValue == -1){
            Intent switchActivityIntent = new Intent(AddCardsActivity.this, MainActivity.class);
            startActivity(switchActivityIntent);
        }
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(AddCardsActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}