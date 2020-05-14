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
                                if (shortField.equals("distLot02")){
                                    tempLot = "2";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/02");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot04")){
                                    tempLot = "4";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/04");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot05")){
                                    tempLot = "5";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/05");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot06")){
                                    tempLot = "6";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/06");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot07")){
                                    tempLot = "7";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/07");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot09")){
                                    tempLot = "9";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/09");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot10")){
                                    tempLot = "10";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/10");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot13")){
                                    tempLot = "13";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/13");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot14")){
                                    tempLot = "14";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/14");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot15")){
                                    tempLot = "15";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/15");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot16")){
                                    tempLot = "16";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/16");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot18")){
                                    tempLot = "18";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/18");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
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
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });
                                }
                                else if (shortField.equals("distLot20")){
                                    tempLot = "20";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/20");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
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
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
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
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot24")){
                                    tempLot = "24";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/24");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot25")){
                                    tempLot = "25";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/25");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot26")){
                                    tempLot = "26";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/26");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot27")){
                                    tempLot = "27";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/27");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot28")){
                                    tempLot = "28";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/28");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
                                else if (shortField.equals("distLot32")){
                                    tempLot = "32";
                                    mLotRef = FirebaseFirestore.getInstance().document("lots/32");
                                    mLotRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Log.d("Searching...","Found lot");
                                                    if (user.getDecal().equals("Student") || user.getDecal().equals("Commuter") && document.getLong(STUDENTSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(STUDENTSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                    else if ((user.getDecal().equals("Faculty")) && document.getLong(FACULTYSPOT_KEY).intValue() > 0){
                                                        parkingTextView.setText("The closest lot is lot " + tempLot);
                                                        spotsTextView.setText("Number of spots remaining: " + document.getLong(FACULTYSPOT_KEY).intValue());
                                                        distanceTextView.setText("The distance from this lot to " + user.getBuilding() + " is " + shortDist + " feet");
                                                    }
                                                }
                                                else {
                                                    Log.d("Searching...","Could not find lot " + tempLot);
                                                }
                                            }
                                        }
                                    });}
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
