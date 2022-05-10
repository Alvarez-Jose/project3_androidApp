package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.activities.LoginActivity.idValue;
import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.adapters.TransactionResultsAdapter;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.TransactionDao;
import com.example.project3_androidapp.db.TransactionEntity;
import com.example.project3_androidapp.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TransactionsActivity extends AppCompatActivity {

    private Button transactionButton, backButton;
    private TextView text;
    private RecyclerView transactions;
    private static int idValue;

    private SharedPreferences mPrefs;
    private List<TransactionEntity> transactionsList = new ArrayList<>();
    private TransactionResultsAdapter transactionAdapter;
    private TransactionDao transactionDao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
//        System.out.println("-=-\tTransactions\t-=-");
        getDatabase();

        backButton = findViewById(R.id.backButtonTransaction);
        transactionButton = findViewById(R.id.newTransactionButton);
//        transactions = findViewById(R.id.viewTransactions);
        text = findViewById(R.id.textView2);

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);

//        text.setText(transactionDao.getAllTransactionsById(idValue).toString());
//        text.setText(transactionDao.getAllTransactions().get(0).getAmount());

        transactionAdapter = new TransactionResultsAdapter(TransactionsActivity.this);

        RecyclerView recyclerView = transactions;
        recyclerView.setLayoutManager(new LinearLayoutManager(TransactionsActivity.this));
        recyclerView.setAdapter(transactionAdapter);

        refreshList();

        View.OnClickListener handler = v -> {

            if (v == transactionButton) {
                switchToCreateTransaction();
            }

            if(v == backButton){
                logOut();
            }

        };

        backButton.setOnClickListener(handler);
        transactionButton.setOnClickListener(handler);
    }

    // get instance of database and return user DAO
    private void getDatabase() {
        db = AppDatabase.getInstance(getApplicationContext());
        transactionDao = db.transactionDao();
    }

    // refresh the user list
    public void refreshList() {
        if (transactionsList != null && transactionDao != null && transactionAdapter != null) {
            transactionsList.clear();
            transactionsList = transactionDao.getTransactionById(idValue);
            transactionAdapter.setResults(transactionsList);
        }
    }

//    private void checkLogin() {
//        if(idValue == -1){
//            Intent switchActivityIntent = new Intent(TransactionsActivity.this, MainActivity.class);
//            startActivity(switchActivityIntent);
//        }
//    }

    private void switchToCreateTransaction() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, CreateTransactionActivity.class);
        startActivity(switchActivityIntent);
    }

    private void logOut() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

}