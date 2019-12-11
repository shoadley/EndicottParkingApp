package com.shoadley.endicottparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    TextView homeTextView;
    Intent incomingIntent;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        incomingIntent = getIntent();
        user = (User)incomingIntent.getSerializableExtra("User");
        homeTextView = findViewById(R.id.textViewHome);

        String userId = user.getId();
        homeTextView.setText("Welcome " + userId);
    }
}
