package com.example.project3_androidapp.activities;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class ProfileActivity extends AppCompatActivity {

    private Button backButton, toEditButton;
    private TextView username, bank;
    private static TransactionResultsAdapter transactionsAdapter;
    private static TransactionDao transactionDao;
    private static List<TransactionEntity> transactions = new ArrayList<>();
    private static int userId;
    private int idValue;

    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getDatabase();
        // set this for a static context
        userId = idValue;

        System.out.println("profile");
        checkLogin();
        setUp();
        // get user's shared preferences
//        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);
        transactionsAdapter = new TransactionResultsAdapter(this);

        backButton = findViewById(R.id.backButton);
        username = findViewById(R.id.userNameText);
        bank = findViewById(R.id.bankText);

        // set up the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewTransactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(transactionsAdapter);
        refreshList();

        View.OnClickListener handler = v -> {

            if (v == toEditButton) {
                switchToEdit();
            }

            if(v == backButton){
                goBack();
            }

        };

        backButton.setOnClickListener(handler);
        toEditButton.setOnClickListener(handler);
    }

    private void setUp() {
        String url = URL_BASE + "/retrieve_transactions_and/?uid=" + userId;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.toString();
            String obj, val;
            int i = 0;
            while (!str.substring(0).equals("]")){
                // find objects with {}
                if(str.substring(0,1).equals("{")){
                    while (!str.substring(0,1).equals("}")){
                        // get things to import with "":
                        obj = str.substring(0, str.indexOf('"'));
                            // get substring from end of ':' to ','
                        TransactionEntity t = new TransactionEntity();
                        if(obj.equals("transaction_id")) {
                            val = str.substring(0, str.indexOf(','));
                            t.setTransactionId(Integer.parseInt(val));
                        } else if(obj.equals("amount")) {
                            val = str.substring(0, str.indexOf(','));
                            t.setAmount(Integer.parseInt(val));
                        } else if(obj.equals("currency")) {
                            val = str.substring(0, str.indexOf(','));
                                t.setCurrency(val);
                        } else if(obj.equals("is_finalized")) {
                            val = str.substring(0, str.indexOf(','));
                            t.setIsFinalized(Integer.parseInt(val));
                        } else if(obj.equals("sending_id")) {
                            val = str.substring(0, str.indexOf(','));
                            t.setSendingId(Integer.parseInt(val));
                        } else if(obj.equals("receiving_id")) {
                            val = str.substring(0, str.indexOf(','));
                            t.setReceivingId(Integer.parseInt(val));
                        } else if(obj.equals("description")) {
                            val = str.substring(0, str.indexOf(','));
                            t.setDescription(val);
                        }
                        // fill with item after import
                        transactionDao.insertTransaction(t);
                        System.out.println(t);
                    }
                }
                str = str.substring(1);
            }
        }, err -> {
            Toast.makeText(getApplicationContext(), "profile error", Toast.LENGTH_LONG).show();
            System.out.println("Profile Err");
        });
        queue.add(stringRequest);
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
            transactions = transactionDao.getTransactionById(userId);
            transactionsAdapter.setResults(transactions);
        }
    }

    private void checkLogin() {
        if(idValue == -1){
            Intent switchActivityIntent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(switchActivityIntent);
        }
    }

    private void showCards(){

    }

    private void showFriends(){

    }

    private void switchToEdit() {
        Intent switchActivityIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(switchActivityIntent);
    }

    private void goBack() {
        Intent switchActivityIntent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }

}