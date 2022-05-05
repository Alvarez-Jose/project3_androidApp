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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.util.Constants;

public class CreateTransactionActivity extends AppCompatActivity {

    private Button submitButton, backButton;
    private TextView transactionAmount, userId;
    private int idValue;
    private double amountValue;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

        checkLogin();

        // get user's shared preferences
//        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);

        submitButton = findViewById(R.id.submitLogin);
        backButton = findViewById(R.id.backButton);
        transactionAmount = findViewById(R.id.transactionText);
//        userId = findViewById(R.id.);

        View.OnClickListener handler = v -> {

            if (v == submitButton) {

                String url = URL_BASE + "/retrieve_user/?user=&pass";

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                    Toast.makeText(getApplicationContext(), "Logged in!", Toast.LENGTH_LONG).show();
                    //Login success path
                }, err -> {
                    Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG).show();
                    switchToMain();
                });
                queue.add(stringRequest);
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
            Intent switchActivityIntent = new Intent(CreateTransactionActivity.this, MainActivity.class);
            startActivity(switchActivityIntent);
        }
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(CreateTransactionActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}