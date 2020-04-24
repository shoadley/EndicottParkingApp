package com.shoadley.endicottparkingapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

public class ParkingActivity extends AppCompatActivity {

    private User user;
    private Intent incomingIntent;
    private DocumentReference mBuildingsRef;
    private DocumentReference mLotRef;
    private String tempLot = "null";
    private TextView parkingTextView;
    private TextView spotsTextView;
    private TextView distanceTextView;
    private int shortDist = 999999;
    private String shortField;

    public static final String STUDENTSPOT_KEY = "studentSpots";
    public static final String FACULTYSPOT_KEY = "facultySpots";
    public static final String COMMUTERSPOT_KEY = "commuterSpots";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        parkingTextView = findViewById(R.id.parkingTextView);
        spotsTextView = findViewById(R.id.spotsTextView);
        distanceTextView = findViewById(R.id.distanceTextView);
        incomingIntent = getIntent();
        user = (User) incomingIntent.getSerializableExtra("User");

            mBuildingsRef = FirebaseFirestore.getInstance().document("buildings/" + user.getBuilding());
            mBuildingsRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                List<String> valueList = new ArrayList<>();
                                List<String> fieldList = new ArrayList<>();

                                Map<String, Object> map = document.getData();
                                if (map != null) {
                                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                                        valueList.add(entry.getValue().toString());
                                        fieldList.add(entry.getKey());
                                        Log.d("entry.getKey()",entry.getKey());
                                    }
                                }

                                for (int i = 0; i < valueList.size(); i++) {
                                    if(Integer.parseInt(valueList.get(i)) < shortDist){
                                        shortDist = Integer.parseInt(valueList.get(i));
                                        shortField = fieldList.get(i);
                                    }
                                }
                                Log.d("Short Field",shortField);
                                if (shortField.equals("distLot1")){tempLot = "01";}
                                else if (shortField.equals("distLot2")){tempLot = "02";}
                                else if (shortField.equals("distLot3")){tempLot = "03";}
                                else if (shortField.equals("distLot4")){tempLot = "04";}
                                else if (shortField.equals("distLot5")){tempLot = "05";}
                                else if (shortField.equals("distLot6")){tempLot = "06";}
                                else if (shortField.equals("distLot7")){tempLot = "07";}
                                else if (shortField.equals("distLot8")){tempLot = "08";}
                                else if (shortField.equals("distLot9")){tempLot = "09";}
                                else if (shortField.equals("distLot10")){tempLot = "10";}
                                else if (shortField.equals("distLot11")){tempLot = "11";}
                                else if (shortField.equals("distLot12")){tempLot = "12";}
                                else if (shortField.equals("distLot13")){tempLot = "13";}
                                else if (shortField.equals("distLot14")){tempLot = "14";}
                                else if (shortField.equals("distLot15")){tempLot = "15";}
                                else if (shortField.equals("distLot16")){tempLot = "16";}
                                else if (shortField.equals("distLot17")){tempLot = "17";}
                                else if (shortField.equals("distLot18")){tempLot = "18";}
                                else if (shortField.equals("distLot19")){
                                    tempLot = "19";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/19");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot20")){tempLot = "20";}
                                else if (shortField.equals("distLot21")){tempLot = "21";}
                                else if (shortField.equals("distLot22")){
                                    tempLot = "22";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/22");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot23")){
                                    tempLot = "23";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/23");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot24")){tempLot = "24";}
                                else if (shortField.equals("distLot25")){tempLot = "25";}
                                else if (shortField.equals("distLot26")){tempLot = "26";}
                            }
                        }
                     else {
                        Log.d("Error", "Error getting documents: ", task.getException());
                    }
                }
            });

        Log.d("tempLot", tempLot);
//        mLotRef = FirebaseFirestore.getInstance().document("lots/19");
//        mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("Searching...","Found lot");
//                        if (user.getDecal().equals("Student") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
//                            parkingTextView.setText("The closest lot is lot " + tempLot);
//                            spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
//                            distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
//                        }
//                    }
//                    else {
//                        Log.d("Searching...","Could not find lot " + tempLot);
//                    }
//                }
//            }
//        });
    }

    public void toHomePage(View view) {
        Intent intent = new Intent(ParkingActivity.this, HomePageActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}
