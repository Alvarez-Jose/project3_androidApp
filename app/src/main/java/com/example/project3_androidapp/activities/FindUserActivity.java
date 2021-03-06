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
import com.example.project3_androidapp.db.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class FindUserActivity extends AppCompatActivity {

    private RecyclerView users;
    private Button searchButton, backButton;
    private TextView userSearch;
    private String usernameText = "";

    private UserResultsAdapter searchResults;
    private UserDao userDao;
    private AppDatabase db;
    private List<UserEntity> searchUserList = new ArrayList<>(), searchResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        db = AppDatabase.getInstance(getApplicationContext());
        userDao = db.userDao();

        backButton = findViewById(R.id.returnButton);
        userSearch = findViewById(R.id.editTextPersonName);
        searchButton = findViewById(R.id.searchButton);
        users = findViewById(R.id.userSearchRecyclerView);

        searchResults = new UserResultsAdapter(FindUserActivity.this);
        searchUserList = userDao.getAllUsers();

        searchResults.setResults(searchUserList);

        RecyclerView recyclerView = users;
        recyclerView.setLayoutManager(new LinearLayoutManager(FindUserActivity.this));
        recyclerView.setAdapter(searchResults);


        View.OnClickListener handler = v -> {

            if (v == searchButton) {

                usernameText = userSearch.getText().toString();
                searchResult.clear();
                for (UserEntity u : searchUserList) {
                    if(u.getUsername().contains(usernameText))
                        searchResult.add(u);
                }
                refreshList();
            }

            if (v == backButton) {
                back();
            }
        };

        searchButton.setOnClickListener(handler);
    }

    // refresh the user list
    public void refreshList() {
//        System.out.println(usernameText);

        if (searchUserList != null && userDao != null && searchResults != null) {
            usernameText = userSearch.getText().toString();
//            searchUserList.clear();
            System.out.println("searchUserList ->" + searchResult);
            searchResults.setResults(searchResult);

        }
    }


    private void back() {
        Intent switchActivityIntent = new Intent(FindUserActivity.this, TransactionsActivity.class);
        startActivity(switchActivityIntent);
    }

}