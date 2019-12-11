package com.shoadley.endicottparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    public static final String ID_KEY = "id";
    public static final String PASSWORD_KEY = "password";
    private DocumentReference mUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToRegistration(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        EditText idView = findViewById(R.id.editTextLoginId);
        EditText passwordView = findViewById(R.id.editTextLoginPassword);

        String idText = idView.getText().toString();
        final String passwordText = passwordView.getText().toString();
        if (idText.isEmpty() || passwordText.isEmpty()) {return;}
        mUserRef = FirebaseFirestore.getInstance().document("users/" + idText);
        mUserRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Log.d("Existence", "User exists, go ahead with password check.");
                    String passwordActualText = documentSnapshot.getString(PASSWORD_KEY);
                    if (passwordText.equals(passwordActualText)) {
                        User user = documentSnapshot.toObject(User.class);
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        intent.putExtra("User", user);
                        startActivity(intent);
                    } else {
                        Log.d("Password", "Password is incorrect");
                    }
                } else {
                    Log.d("Existence", "Incorrect username does not exist.");
                }
            }
        });
    }
}
