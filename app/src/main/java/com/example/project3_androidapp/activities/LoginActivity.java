package com.example.project3_androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.project3_androidapp.MainActivity;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.registerActivity;

public class LoginActivity extends AppCompatActivity {

    Button toMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO get login activity fields
//        toMainButton = (Button) findViewById(R.id.loginToMain);

        View.OnClickListener handler = v -> {

            // if login is successful
//            SharedPreferences.Editor editor = mSharedPrefs.edit();
//            editor.putInt(Constants.USER_ID_KEY, mUserId);
//            editor.apply();

            if (v == toMainButton) {
                // doStuff
                Intent intentMain = new Intent(LoginActivity.this,
                        MainActivity.class);
                LoginActivity.this.startActivity(intentMain);
                System.out.println("TO MAIN");
            }

        };

        toMainButton.setOnClickListener(handler);
    }
}