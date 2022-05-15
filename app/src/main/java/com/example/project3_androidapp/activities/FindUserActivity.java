package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.project3_androidapp.adapters.UserResultsAdapter;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.TransactionEntity;
import com.example.project3_androidapp.db.UserDao;

import java.util.ArrayList;
import java.util.List;

public class FindUserActivity extends AppCompatActivity {

    RecyclerView users;
    private String usernameText;
    Button searchButton;
    TextView userSearch;

    UserResultsAdapter searchResults;
    UserDao userDao;
    AppDatabase db;
    private List<TransactionEntity> searchUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        getDatabase();

        userSearch = findViewById(R.id.editTextPersonName);
        searchButton = findViewById(R.id.searchButton);
        users = findViewById(R.id.userSearchRecyclerView);

        searchResults = new UserResultsAdapter(FindUserActivity.this);
        searchResults.setResults(userDao.getAllUsers());

        RecyclerView recyclerView = users;
        recyclerView.setLayoutManager(new LinearLayoutManager(FindUserActivity.this));
        recyclerView.setAdapter(searchResults);

//        refreshList();
//
//        View.OnClickListener handler = v -> {
//
//            if (v == searchButton) {
//                findUser();
//            }
//
//
//        };

//        searchButton.setOnClickListener(handler);
    }

    // get instance of database and return user DAO
    private void getDatabase() {
        db = AppDatabase.getInstance(getApplicationContext());
        userDao = db.userDao();
    }

    // refresh the user list
    public void refreshList(String username) {
//        if (searchUserList != null && userDao != null && searchResults != null) {
//            searchUserList.clear();
//            searchUserList = userDao.getUserByName(username);
//            searchResults.setResults(searchUserList);
//        }
    }

    public void findUser(String username){
        String usernameText = userSearch.getText().toString();

        String url = URL_BASE + "/retrieve_user/?userId=" + usernameText;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {

        }, err -> {
            Toast.makeText(getApplicationContext(), "Error sending request.", Toast.LENGTH_SHORT).show();
            back();
        });
        queue.add(stringRequest);
        refreshList(username);
    }

    private void back() {
        Intent switchActivityIntent = new Intent(FindUserActivity.this, TransactionsActivity.class);
        startActivity(switchActivityIntent);
    }

}