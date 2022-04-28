package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;

public class FriendsProfileActivity extends AppCompatActivity {

    private TextView friendName;
    private RecyclerView transactions, otherFriends;
    private Button backButton, addFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_profile);

//        String url = URL_BASE + "/retrieve_user/?user=&pass";
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
//            Toast.makeText(getApplicationContext(), "Logged in!", Toast.LENGTH_LONG).show();
//        }, err -> {
//            Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG).show();
//            switchToMain();
//        });
//        queue.add(stringRequest);

//        View.OnClickListener handler = v -> {

//            if (v == addFriend) {
//                newFriend();
//            }

//            if(v == backButton){
//                switchToMain();
//            }

//        };

//        backButton.setOnClickListener(handler);
//        submitButton.setOnClickListener(handler);
    }

    private void newFriend() {
        Intent switchActivityIntent = new Intent(FriendsProfileActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(FriendsProfileActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}