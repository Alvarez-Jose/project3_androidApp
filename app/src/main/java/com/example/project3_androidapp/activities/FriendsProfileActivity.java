package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.project3_androidapp.LoginActivity;
import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.util.Constants;

public class FriendsProfileActivity extends AppCompatActivity {

    private TextView friendName;
    private RecyclerView transactions, otherFriends;
    private Button backButton, addFriend;
    private int idValue;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_profile);

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);
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