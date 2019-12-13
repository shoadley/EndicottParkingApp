package com.shoadley.endicottparkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ParkingActivity extends AppCompatActivity {

    private User user;
    private Intent incomingIntent;
    private CollectionReference mLotsRef;
    private DocumentReference mLotRef;
    private DocumentReference mUserRef;
    private ParkingLot tempLot;
    private ParkingLot currentLot;
    private TextView parkingTextView;
    private TextView spotsTextView;
    private TextView distanceTextView;
    private Button parkingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        parkingTextView = findViewById(R.id.parkingTextView);
        spotsTextView = findViewById(R.id.spotsTextView);
        distanceTextView = findViewById(R.id.distanceTextView);
        parkingButton = findViewById(R.id.parkButton);
        incomingIntent = getIntent();
        user = (User)incomingIntent.getSerializableExtra("User");

        // If the user is not parked
        if (user.getCurrentLot().equals("0")) {
            parkingButton.setText("Park");
            currentLot = new ParkingLot(0, 0, 0, 0, 9999999, 9999999, 9999999);
            mLotsRef = FirebaseFirestore.getInstance().collection("lots");
            mLotsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            tempLot = document.toObject(ParkingLot.class);
                            Log.d("Templot check", "Templot stats are " + tempLot.getId() + " " +tempLot.getDistanceBeacon() + " " + tempLot.getStudentSpots());
                            if (user.getDecal().equals("Student") && tempLot.getStudentSpots() > 0 && user.getBuilding().equals("Beacon") && tempLot.getDistanceBeacon() < currentLot.getDistanceBeacon()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Student") && tempLot.getStudentSpots() > 0 && user.getBuilding().equals("Standish") && tempLot.getDistanceStandish() < currentLot.getDistanceStandish()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Student") && tempLot.getStudentSpots() > 0 && user.getBuilding().equals("LSB") && tempLot.getDistanceLSB() < currentLot.getDistanceLSB()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Faculty") && tempLot.getFacultySpots() > 0 && user.getBuilding().equals("Beacon") && tempLot.getDistanceBeacon() < currentLot.getDistanceBeacon()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Faculty") && tempLot.getFacultySpots() > 0 && user.getBuilding().equals("Standish") && tempLot.getDistanceStandish() < currentLot.getDistanceStandish()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Faculty") && tempLot.getFacultySpots() > 0 && user.getBuilding().equals("LSB") && tempLot.getDistanceLSB() < currentLot.getDistanceLSB()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Commuter") && tempLot.getCommuterSpots() > 0 && user.getBuilding().equals("Beacon") && tempLot.getDistanceBeacon() < currentLot.getDistanceBeacon()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Commuter") && tempLot.getCommuterSpots() > 0 && user.getBuilding().equals("Standish") && tempLot.getDistanceStandish() < currentLot.getDistanceStandish()) {
                                currentLot = tempLot;
                            }
                            else if (user.getDecal().equals("Commuter") && tempLot.getCommuterSpots() > 0 && user.getBuilding().equals("LSB") && tempLot.getDistanceLSB() < currentLot.getDistanceLSB()) {
                                currentLot = tempLot;
                            }
                        }

                        user.setCurrentLot(Integer.toString(currentLot.getId()));
                        parkingTextView.setText("The closest lot is lot " + currentLot.getId());
                        if (user.getDecal().equals("Student")) {
                            spotsTextView.setText("Number of spots remaining" + currentLot.getStudentSpots());
                        }
                        else if (user.getDecal().equals("Faculty")) {
                            spotsTextView.setText("Number of spots remaining" + currentLot.getFacultySpots());
                        }
                        else if (user.getDecal().equals("Commuter")) {
                            spotsTextView.setText("Number of spots remaining" + currentLot.getCommuterSpots());
                        }

                        if (user.getBuilding().equals("Beacon")) {
                            distanceTextView.setText("The distance from this lot to Beacon is" + currentLot.getDistanceBeacon());
                        }
                        else if (user.getBuilding().equals("Standish")) {
                            distanceTextView.setText("The distance from this lot to Standish is" + currentLot.getDistanceStandish());
                        }
                        else if (user.getBuilding().equals("LSB")) {
                            distanceTextView.setText("The distance from this lot to the LSB is" + currentLot.getDistanceLSB());
                        }

                        parkingButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mUserRef = FirebaseFirestore.getInstance().document("users/" + user.getId());
                                mUserRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        mUserRef.update("currentLot", Integer.toString(currentLot.getId()));
                                    }
                                });
                                mLotRef = FirebaseFirestore.getInstance().document("lots/" + currentLot.getId());
                                mLotRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (user.getDecal().equals("Student")) {
                                            mLotRef.update("studentSpots", currentLot.getStudentSpots() - 1);
                                        }
                                        else if (user.getDecal().equals("Faculty")) {
                                            mLotRef.update("facultySpots", currentLot.getFacultySpots() - 1);
                                        }
                                        else if (user.getDecal().equals("Commuter")) {
                                            mLotRef.update("commuterSpots", currentLot.getCommuterSpots() - 1);
                                        }
                                    }
                                });

                                parkingButton.setText("Un-Park");
                            }
                        });
                    } else {
                        Log.d("Error", "Error getting documents: ", task.getException());
                    }
                }
            });
        }

        // If the user is parked
        else {
            parkingButton.setText("Un-Park");
            mLotRef = FirebaseFirestore.getInstance().document("lots/" + user.getCurrentLot());
            mLotRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    currentLot = documentSnapshot.toObject(ParkingLot.class);

                    parkingTextView.setText("The closest lot is lot " + currentLot.getId());
                    if (user.getDecal().equals("Student")) {
                        spotsTextView.setText("Number of spots remaining" + currentLot.getStudentSpots());
                    }
                    else if (user.getDecal().equals("Faculty")) {
                        spotsTextView.setText("Number of spots remaining" + currentLot.getFacultySpots());
                    }
                    else if (user.getDecal().equals("Commuter")) {
                        spotsTextView.setText("Number of spots remaining" + currentLot.getCommuterSpots());
                    }

                    if (user.getBuilding().equals("Beacon")) {
                        distanceTextView.setText("The distance from this lot to Beacon is" + currentLot.getDistanceBeacon());
                    }
                    else if (user.getBuilding().equals("Standish")) {
                        distanceTextView.setText("The distance from this lot to Standish is" + currentLot.getDistanceStandish());
                    }
                    else if (user.getBuilding().equals("LSB")) {
                        distanceTextView.setText("The distance from this lot to the LSB is" + currentLot.getDistanceLSB());
                    }
                }
            });

            parkingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mUserRef = FirebaseFirestore.getInstance().document("users/" + user.getId());
                    mUserRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            mUserRef.update("currentLot", "0");
                        }
                    });
                    mLotRef = FirebaseFirestore.getInstance().document("lots/" + currentLot.getId());
                    mLotRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (user.getDecal().equals("Student")) {
                                mLotRef.update("studentSpots", currentLot.getStudentSpots() + 1);
                            }
                            else if (user.getDecal().equals("Faculty")) {
                                mLotRef.update("facultySpots", currentLot.getFacultySpots() + 1);
                            }
                            else if (user.getDecal().equals("Commuter")) {
                                mLotRef.update("commuterSpots", currentLot.getCommuterSpots() + 1);
                            }
                        }
                    });

                    parkingButton.setText("Park");
                }
            });
        }
    }

    public void toHomePage(View view) {
        Intent intent = new Intent(ParkingActivity.this, HomePageActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}
