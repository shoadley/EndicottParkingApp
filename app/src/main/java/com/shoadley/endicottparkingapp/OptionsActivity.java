package com.shoadley.endicottparkingapp;

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

import javax.annotation.Nonnull;

public class OptionsActivity extends AppCompatActivity {

    private Intent incomingIntent;
    private User user;
    private DocumentReference mUserRef;

    public static final String ID_KEY = "id";
    public static final String PASSWORD_KEY = "password";
    public static final String DECAL_KEY = "decal";
    public static final String BUILDING_KEY = "building";
    public static final String EMAIL_KEY = "email";
    public static final String CURRENT_LOT_KEY = "currentLot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Start","Creating options");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        incomingIntent = getIntent();
        user = (User)incomingIntent.getSerializableExtra("User");
        EditText emailOptionsEditText = findViewById(R.id.emailOptionsEditText);
        emailOptionsEditText.setText(user.getEmail());
    }

    public void saveUser(View view){
        EditText emailOptionsEditText = findViewById(R.id.emailOptionsEditText);
        Spinner buildingOptionsSpinner = findViewById(R.id.buildingOptionsSpinner);
        Spinner decalOptionsSpinner = findViewById(R.id.decalOptionsSpinner);


        final String emailText = emailOptionsEditText.getText().toString();
        final String buildingText = buildingOptionsSpinner.getSelectedItem().toString();
        final String decalText = decalOptionsSpinner.getSelectedItem().toString();

        mUserRef = FirebaseFirestore.getInstance().document("users/" + user.getId());
        mUserRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                        Map<String, Object> dataToSave = new HashMap<>();
                        dataToSave.put(ID_KEY, user.getId());
                        dataToSave.put(PASSWORD_KEY, user.getPassword());
                        dataToSave.put(EMAIL_KEY,emailText);
                        dataToSave.put(DECAL_KEY,decalText);
                        dataToSave.put(BUILDING_KEY,buildingText);
                        dataToSave.put(CURRENT_LOT_KEY,"0");
                        mUserRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("User","User has been updated!");
                                user.setEmail(emailText);
                                user.setDecal(decalText);
                                user.setBuilding(buildingText);
                                Intent intent = new Intent(OptionsActivity.this, HomePageActivity.class);
                                intent.putExtra("User", user);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@Nonnull Exception e) {
                                Log.w("User", "User was not created!");
                            }
                        });
                } else {
                    Log.d("Existence", "Failed with: ", task.getException());
                }
            }
        });
    }

    public void exit(View view){
        Intent intent = new Intent(OptionsActivity.this, HomePageActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}
