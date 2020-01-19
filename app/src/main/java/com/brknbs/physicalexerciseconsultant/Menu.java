package com.brknbs.physicalexerciseconsultant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Menu extends AppCompatActivity {

    Button exercisesButton;
    Button exerciseListButton;
    Button logOutButton;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    FirebaseUser user;
    String userID;
    String userName;

    TextView showUserName;
    TextView welcomeMessage;

    int hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");

        user = mAuth.getCurrentUser();
        userID = user.getUid();

        exercisesButton = findViewById(R.id.exercisesButton);
        exerciseListButton = findViewById(R.id.exerciseListButton);
        logOutButton = findViewById(R.id.logOutButton);
        showUserName = findViewById(R.id.showUserName);
        welcomeMessage = findViewById(R.id.welcomeMessage);

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child(userID).child("Name").getValue(String.class);
                if((hour >= 6) && (hour <= 12)){
                    welcomeMessage.setText("GOOD MORNING," + userName.toUpperCase());
                }
                else if((hour >= 13) && (hour <= 18)){
                    welcomeMessage.setText("GOOD AFTERNOON," + userName.toUpperCase());
                }
                else if((hour >= 19) && (hour <= 23)){
                    welcomeMessage.setText("GOOD EVENING," + userName.toUpperCase());
                }
                else {
                    welcomeMessage.setText("GOOD NIGHT," + userName.toUpperCase());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        exercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,ExerciseCategories.class);
                startActivity(i);
            }
        });

        exerciseListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,MyLists.class);
                startActivity(i);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                Intent intent = new Intent(Menu.this,Login.class);
                startActivity(intent);
            }
        });



    }
}
