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
import java.util.concurrent.atomic.AtomicReference;

public class TransactionsActivity extends AppCompatActivity {

    private Button transactionButton, backButton;
    private RecyclerView transactions;
    private static int idValue;

    private SharedPreferences mPrefs;
    private static List<TransactionEntity> transactionsList = new ArrayList<>();
    private static TransactionResultsAdapter transactionAdapter;
    private static TransactionDao transactionDao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        getDatabase();

        checkLogin();

        backButton = findViewById(R.id.backButtonTransaction);
        transactionButton = findViewById(R.id.newTransactionButton);
        transactions = findViewById(R.id.recyclerViewTransactions);

        setUp();
        // get user's shared preferences
//        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);

        transactionAdapter = new TransactionResultsAdapter(this);
        refreshList();

        RecyclerView recyclerView = transactions;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(transactionAdapter);

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

    private void loadTransactions() {
        AtomicReference<String> responseModified = new AtomicReference<>("");
        String url = URL_BASE + "/retrieve_transactions_and/?uid=" + idValue;
        // use the api in order to find the account on the database.

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            responseModified.set(response.replaceAll("&#34;", "\""));
            System.out.println(" Transaction - "+ responseModified);
        }, err -> {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        });
        queue.add(stringRequest);

        //Toast.makeText(getApplicationContext(), "finished loading transactions", Toast.LENGTH_SHORT).show();
    }

    private void setUp() {
        String url = URL_BASE + "/retrieve_transactions_and/?uid=" + idValue;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.replaceAll("&#34;", "\"");
            String obj, var;

            System.out.println(str);
            // this algorithm parses the string and stores the values into the DAO
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '{'){
                    TransactionEntity t = new TransactionEntity();
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);
                        i = str.indexOf(':', i)+1;
                        if(obj.equals("transaction_id")){
                            var = str.substring(i, str.indexOf(',', i));
                            t.setTransactionId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("amount")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setAmount(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("currency")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCurrency(var);
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("is_finalized")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setIsFinalized(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("sending_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setSendingId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("receiving_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setReceivingId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("description")) {
                            var = str.substring(i, str.indexOf('\"', i));
                            t.setDescription(var);
                            i = str.indexOf(',', i)-1;
                        }

                        if(i >= str.length() || i < 0)
                            break;
                    }
//                    System.out.println(t.getTransactionId() + " " + t.getSendingId());
                    transactionDao.insertTransaction(t);
                }
                if(i >= str.length() || i < 0)
                    break;
            }
            System.out.println(transactionDao.getAllTransactions());
        }, err -> {
            Toast.makeText(getApplicationContext(), "profile error", Toast.LENGTH_LONG).show();
            System.out.println("Profile Err");
        });
        queue.add(stringRequest);
    }
    // get instance of database and return user DAO
    private void getDatabase() {
        db = AppDatabase.getInstance(getApplicationContext());
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

    private void checkLogin() {
        if(idValue == -1){
            Intent switchActivityIntent = new Intent(TransactionsActivity.this, MainActivity.class);
            startActivity(switchActivityIntent);
        }
    }

    private void switchToCreateTransaction() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, CreateTransactionActivity.class);
        startActivity(switchActivityIntent);
    }

    private void logOut() {
        Intent switchActivityIntent = new Intent(TransactionsActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}