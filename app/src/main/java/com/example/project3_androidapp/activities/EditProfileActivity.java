package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.UserDao;
import com.example.project3_androidapp.util.Constants;
import com.google.firebase.firestore.auth.User;

public class EditProfileActivity extends AppCompatActivity {

    private Button submitButton, backButton;
    private EditText newName, newPass, newBank, added;
    private TextView username;
    private String nameText, passText;
    private Double bankAmt, addedValue;
    private int idValue;

    AppDatabase appDatabase;
    UserDao userDao;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // get user's shared preferences
        mPrefs = this.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        idValue = mPrefs.getInt(Constants.USER_ID_KEY, -1);

        getDatabase();

        // items in activity
        username = findViewById(R.id.userName);
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton);
        newName = findViewById(R.id.editTextUsername);
        newPass = findViewById(R.id.newPassword);
        newBank = findViewById(R.id.editBankAmount);

        View.OnClickListener handler = v -> {

            if (v == submitButton) {
                editProfile();
                switchToMain();
            }

            if(v == backButton){
                switchToMain();
            }

        };

        backButton.setOnClickListener(handler);
        submitButton.setOnClickListener(handler);
    }

    private void editProfile(){
        String enteredUsername = newName.getText().toString();
        String enteredPassword = newPass.getText().toString();
        double enteredBank = Double.parseDouble(newBank.getText().toString());

        String url = URL_BASE + "/update_user/?userId=" + idValue +
                "&username=" + enteredUsername +
                "&password=" + enteredPassword +
                "&admin=0" +
                "&cardListId=" + idValue +
                "&userListId=" + idValue +
                "&bank=" + enteredBank +
                "&transactionListId=" + idValue;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
            //Login success path
        }, err -> {
            Toast.makeText(getApplicationContext(), "Error sending request.", Toast.LENGTH_SHORT).show();
            switchToMain();
        });
        queue.add(stringRequest);
    }

    private void getDatabase() {
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(EditProfileActivity.this, LoadingActivity.class);
        startActivity(switchActivityIntent);
    }
}