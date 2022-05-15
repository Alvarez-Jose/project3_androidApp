package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.TransactionDao;
import com.example.project3_androidapp.util.Constants;

import java.util.concurrent.atomic.AtomicBoolean;

public class CreateTransactionActivity extends AppCompatActivity {

    private Button submitButton, backButton;
    private TextView transactionAmount;
    private TextView userId;
    private int idValue, userValue;
    private double amountValue;
    private TransactionDao tdb;
    private AppDatabase database;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

        getDatabase();

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);

        submitButton = findViewById(R.id.confirmButton);
        backButton = findViewById(R.id.backButton);
        userId = findViewById(R.id.editOtherUser);
        transactionAmount = findViewById(R.id.editAmount);


        View.OnClickListener handler = v -> {

            if (v == submitButton) {
                userValue = getInt(userId);
                amountValue = getDouble(transactionAmount);

                System.out.println("Submit - - >" + userValue);
                System.out.println("Submit - - >" + amountValue);
//                userValue = Integer.parseInt(userId.toString());
                String url = URL_BASE + "/create_transaction/?tid=" + (tdb.getHighestId() + 1) + "&amt=" + amountValue + "&cur=$&fin=0&sid=" + idValue + "&rid=" + userValue + "&desc=fromAndroid"; // TODO change url to add necessary fields
                AtomicBoolean success = new AtomicBoolean(false);


                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
//                    System.out.println("Create - - >" + response);
                    Toast.makeText(getApplicationContext(), "Transaction sent!", Toast.LENGTH_LONG).show();
                    if (!response.contains("sorry, something went wrong with your request.")) {
                        success.set(true);
                    } else {
                        success.set(false);
                    }
                }, err -> {
                    Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG).show();
                    back();
                });
                queue.add(stringRequest);
                // in order to make sure the transaction is sent, reload the data back into the dao
                if (success.get()) {
                    reloadToTransaction();
                } else reload();
            }

            if (v == backButton) {
                back();
            }

        };

        backButton.setOnClickListener(handler);
        submitButton.setOnClickListener(handler);
    }

    private int getInt(TextView text) {
        String str = text.getText().toString();
        if (str.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
        {
            return Integer.parseInt(str);
        } else {
            System.out.println("not a valid number");
            return -1;
        }
    }

    private double getDouble(TextView text) {
        String str = text.getText().toString();
        if (str.matches("^\\d+(\\.\\d{2})?$")) //check if only digits. Could also be text.matches("[0-9]+")
        {
            return Double.parseDouble(str);
        } else {
            System.out.println("not a valid number");
            return -1.00;
        }
    }

    private void getDatabase() {
        database = AppDatabase.getInstance(getApplicationContext());
        tdb = database.transactionDao();
    }

    private void reloadToTransaction() {
        Intent switchActivityIntent = new Intent(CreateTransactionActivity.this, LoadingActivity.class);
        startActivity(switchActivityIntent);
    }

    public void reload() {
        Intent switchActivityIntent = new Intent(CreateTransactionActivity.this, CreateTransactionActivity.class);
        startActivity(switchActivityIntent);
    }

    private void back() {
        Intent switchActivityIntent = new Intent(CreateTransactionActivity.this, TransactionsActivity.class);
        startActivity(switchActivityIntent);
    }

}