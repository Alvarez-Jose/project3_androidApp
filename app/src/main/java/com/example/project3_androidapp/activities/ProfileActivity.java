package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.activities.LoginActivity.idValue;
import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.project3_androidapp.adapters.TransactionResultsAdapter;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.TransactionDao;
import com.example.project3_androidapp.db.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private Button backButton, toEditButton, addCardButton;
    private TextView username, bank;
    private static TransactionResultsAdapter transactionsAdapter;
    private static TransactionDao transactionDao;
    private static List<TransactionEntity> transactions = new ArrayList<>();

    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getDatabase();
        // set this for a static context

        System.out.println("profile");
        checkLogin();
        // get user's shared preferences
//        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);
        transactionsAdapter = new TransactionResultsAdapter(this);

        addCardButton = findViewById(R.id.addCard);
        toEditButton = findViewById(R.id.editButton);
        backButton = findViewById(R.id.backButton);
        username = findViewById(R.id.userNameText);
        bank = findViewById(R.id.bankText);

        // set up the recycler view
//        RecyclerView recyclerView = findViewById(R.id.viewTransactions);
//        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
//        recyclerView.setAdapter(transactionsAdapter);
//        refreshList();

        View.OnClickListener handler = v -> {

            if (v == toEditButton) {
                switchToEdit();
            }

            if (v == backButton) {
                goBack();
            }

            if (v == addCardButton) {
                switchToCards();
            }
        };

        backButton.setOnClickListener(handler);
        toEditButton.setOnClickListener(handler);
    }

    // get instance of database and return user DAO
    private void getDatabase() {
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        transactionDao = db.transactionDao();
    }

    // refresh the user list
    public static void refreshList() {
        if (transactions != null && transactionDao != null && transactionsAdapter != null) {
            transactions.clear();
            transactions = transactionDao.getTransactionById(idValue);
            transactionsAdapter.setResults(transactions);
        }
    }

    private void checkLogin() {
        if (idValue == -1) {
            Intent switchActivityIntent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(switchActivityIntent);
        }
    }

    private void switchToEdit() {
        Intent switchActivityIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToCards() {
        Intent switchActivityIntent = new Intent(ProfileActivity.this, AddCardsActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goBack() {
        Intent switchActivityIntent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }

}