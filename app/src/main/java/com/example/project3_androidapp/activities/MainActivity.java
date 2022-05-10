package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.project3_androidapp.R;
import com.example.project3_androidapp.util.Constants;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private Button loginButton, signupButton;
//    SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupButton = findViewById(R.id.signUp);
        loginButton = findViewById(R.id.logIn);

        loginButton.setOnClickListener(view -> switchToLogin());

        signupButton.setOnClickListener(view -> switchToRegister());
    }

    private void switchToLogin() {
        Intent switchActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchToRegister() {
        Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
        startActivity(switchActivityIntent);
    }

}