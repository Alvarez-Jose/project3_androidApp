package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.activities.LoginActivity.idValue;
import static com.example.project3_androidapp.util.Constants.URL_BASE;
import static com.example.project3_androidapp.util.Constants.USER_ID_KEY;
import static com.example.project3_androidapp.util.Constants.USER_NAME_KEY;

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

    private Button transactionButton, backButton, editButton, findButton, refresh;
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

        refresh = findViewById(R.id.refreshButton);
        backButton = findViewById(R.id.backButtonTransaction);
        transactionButton = findViewById(R.id.newTransactionButton);
        transactions = findViewById(R.id.viewTransactions);
        findButton = findViewById(R.id.findUserButton);
        editButton = findViewById(R.id.editProfileButton);

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);

//        System.out.println("id = "+idValue);

//        text.setText(transactionDao.getAllTransactionsById(idValue).toString());
//        text.setText(transactionDao.getAllTransactions().get(0).getAmount());

        transactionAdapter = new TransactionResultsAdapter(TransactionsActivity.this);
        transactionAdapter.setResults(transactionDao.getAllTransactionsById(mPrefs.getInt(USER_ID_KEY, 0)));

//        refreshList();

        RecyclerView recyclerView = transactions;
        recyclerView.setLayoutManager(new LinearLayoutManager(TransactionsActivity.this));
        recyclerView.setAdapter(transactionAdapter);


        View.OnClickListener handler = v -> {

            if (v == transactionButton) {
                switchToCreateTransaction();
            }

            if (v == backButton) {
                logOut();
            }

            if (v == findButton) {
                toSearchUser();
            }

            if (v == editButton) {
                toEditProfile();
            }

            if (v == refresh) {
                reload();
            }

        };

        backButton.setOnClickListener(handler);
        transactionButton.setOnClickListener(handler);
        findButton.setOnClickListener(handler);
        editButton.setOnClickListener(handler);
        refresh.setOnClickListener(handler);

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
            transactionsList = transactionDao.getAllTransactionsById(idValue);
            transactionAdapter.setResults(transactionsList);
        }
    }

    private void switchToCreateTransaction() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, CreateTransactionActivity.class);
        startActivity(switchActivityIntent);
    }

    private void logOut() {
        if (mPrefs.getInt(Constants.USER_ID_KEY, -1) != -1) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(Constants.USER_ID_KEY, -1);
            editor.apply();
        } // to remove from automatic login

        Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();

        // force user to come back and login again
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void toSearchUser() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, FindUserActivity.class);
        startActivity(switchActivityIntent);
    }

    private void toEditProfile() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, EditProfileActivity.class);
        startActivity(switchActivityIntent);
    }

    private void reload() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, LoadingActivity.class);
        startActivity(switchActivityIntent);
    }
}