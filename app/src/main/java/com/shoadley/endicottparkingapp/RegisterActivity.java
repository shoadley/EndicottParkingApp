package com.shoadley.endicottparkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    public static final String ID_KEY = "id";
    public static final String PASSWORD_KEY = "password";
    public static final String DECAL_KEY = "decal";
    public static final String BUILDING_KEY = "building";
    public static final String EMAIL_KEY = "email";

    private DocumentReference mUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void createUser(View view) {
        EditText idView = findViewById(R.id.editTextRegisterID);
        EditText emailView = findViewById(R.id.editTextRegisterEmail);
        EditText passwordView = findViewById(R.id.editTextRegisterPassword);
        EditText confPasswordView = findViewById(R.id.editTextConfirmPassword);
        Spinner buildingView = findViewById(R.id.spinnerHomeBuilding);
        Spinner decalView = findViewById(R.id.spinnerDecalType);

        final String idText = idView.getText().toString();
        final String emailText = emailView.getText().toString();
        final String passwordText = passwordView.getText().toString();
        final String confPasswordText = confPasswordView.getText().toString();
        final String buildingText = buildingView.getSelectedItem().toString();
        final String decalText = decalView.getSelectedItem().toString();

        if (idText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || confPasswordText.isEmpty()) {return;}
        mUserRef = FirebaseFirestore.getInstance().document("users/" + idText);
        mUserRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Existence", "User already exists, choose different id");
                    } else {
                        Log.d("Existence", "User does not exist, create new user.");
                        if (!passwordText.equals(confPasswordText)) {
                            Log.d("Password", "Password entries do not match.");
                            return;
                        }
                        Map<String, Object> dataToSave = new HashMap<>();
                        dataToSave.put(ID_KEY,idText);
                        dataToSave.put(PASSWORD_KEY,passwordText);
                        dataToSave.put(EMAIL_KEY,emailText);
                        dataToSave.put(DECAL_KEY,decalText);
                        dataToSave.put(BUILDING_KEY,buildingText);
                        mUserRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("User","User has been created!");
                                User user = new User(idText, passwordText, emailText, decalText, buildingText);
                                Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                                intent.putExtra("User", user);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("User", "User was not created!");
                            }
                        });
                    }
                } else {
                    Log.d("Existence", "Failed with: ", task.getException());
                }
            }
        });
    }
}
