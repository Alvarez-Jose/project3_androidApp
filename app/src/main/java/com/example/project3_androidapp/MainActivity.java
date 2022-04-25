package com.example.project3_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.project3_androidapp.activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupButton = (Button) findViewById(R.id.signUp);
        loginButton = (Button) findViewById(R.id.logIn);

        View.OnClickListener handler = v -> {

            if (v == signupButton) {
                // doStuff
                Intent intentMain = new Intent(MainActivity.this,
                        registerActivity.class);
                MainActivity.this.startActivity(intentMain);
                System.out.println("TO SIGNUP");
            }

            if (v == loginButton) {
                // doStuff
                Intent intentApp = new Intent(MainActivity.this,
                        LoginActivity.class);

                MainActivity.this.startActivity(intentApp);

                System.out.println("TO LOGIN");

            }
        };

        signupButton.setOnClickListener(handler);
        loginButton.setOnClickListener(handler);
    }
}