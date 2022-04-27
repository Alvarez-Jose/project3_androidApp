package com.example.project3_androidapp;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.util.Constants;
import com.google.gson.JsonArray;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView text;
    Button toMainButton;
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = URL_BASE + "/retrieve_user/?userId=1";

        text = findViewById(R.id.exampleText);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                text.setText("Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e){
                text.setText("request error");
            }
        });

        queue.add(stringRequest);

        toMainButton = findViewById(R.id.loginToMain);
        System.out.println(toMainButton);
        toMainButton.setOnClickListener(view -> switchToMain());
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}