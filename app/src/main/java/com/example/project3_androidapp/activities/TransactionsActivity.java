package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;

public class TransactionsActivity extends AppCompatActivity {

    private Button createTransactionButton, backButton;
    private RecyclerView transactions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

//        createTransactionButton = findViewById(R.id);
//        backButton = findViewById(R.id);
//        transactions = findViewById(R.id);

        // TODO recycler view here

//        View.OnClickListener handler = v -> {

//            if (v == createTransactionButton) {
//                switchToCreateTransaction();
//            }

//            if(v == backButton){
//                switchToMain();
//            }

//        };

//        backButton.setOnClickListener(handler);
//        submitButton.setOnClickListener(handler);
    }

    private void switchToCreateTransaction() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, CreateTransactionActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}