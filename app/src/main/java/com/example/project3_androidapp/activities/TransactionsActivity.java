package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Transaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.adapters.TransactionResultsAdapter;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.TransactionDao;
import com.example.project3_androidapp.db.TransactionEntity;
import com.example.project3_androidapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    private Button createTransactionButton, backButton;
    private RecyclerView transactions;
    private static int idValue;

    private SharedPreferences mPrefs;
    private static List<TransactionEntity> transactionsList = new ArrayList<>();
    private static TransactionResultsAdapter transactionAdapter;
    private static TransactionDao transactionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        getDatabase();

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);
//        createTransactionButton = findViewById(R.id);
//        backButton = findViewById(R.id);
//        transactions = findViewById(R.id);

        // TODO recycler view here
        transactionAdapter = new TransactionResultsAdapter(this);
        refreshList();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTransactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(transactionAdapter);

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

    // get instance of database and return user DAO
    private void getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        transactionDao = db.transactionDao();
    }

    // refresh the user list
    public static void refreshList() {
        if (transactionsList != null && transactionDao != null && transactionAdapter != null) {
            transactionsList.clear();
            transactionsList = transactionDao.getTransactionById(idValue);
            transactionAdapter.setResults(transactionsList);
        }
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