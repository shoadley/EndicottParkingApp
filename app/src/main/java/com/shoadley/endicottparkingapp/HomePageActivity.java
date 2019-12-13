package com.shoadley.endicottparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    private TextView homeTextView;
    private Intent incomingIntent;
    private User user;

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

    public void toParking(View view) {
        Intent intent = new Intent(HomePageActivity.this, ParkingActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void toLogin(View view) {
        Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
