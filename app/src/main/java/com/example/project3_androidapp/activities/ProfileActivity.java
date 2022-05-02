package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.util.Constants;

public class ProfileActivity extends AppCompatActivity {

    private Button backButton, toEditButton;
    private TextView username, bank;
    private RecyclerView friends, cards;
    private int idValue;

    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);
        // TODO set text values on this activity to reflect user's profile

//        toEditButton = findViewById(R.id);
//        backButton = findViewById(R.id);
//        username = findViewById(R.id);
//        bank = findViewById(R.id);
//        friends = findViewById(R.id);
//        cards = findViewById(R.id);

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

//            if (v == toEditButton) {
//                switchToMain();
//            }

//            if(v == backButton){
//                switchToMain();
//            }

//        };

//        backButton.setOnClickListener(handler);
//        submitButton.setOnClickListener(handler);
    }

    private void showCards(){

    }

    private void showFriends(){

    }

    private void switchToEdit() {
        Intent switchActivityIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}