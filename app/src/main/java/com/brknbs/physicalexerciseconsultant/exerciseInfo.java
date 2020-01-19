package com.brknbs.physicalexerciseconsultant;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzaitsev.meternumberpicker.MeterNumberPicker;
import com.alexzaitsev.meternumberpicker.MeterView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class exerciseInfo extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myReff;

    FirebaseUser user;
    String userID;

    public String exerciseId;
    public String exerciseDesc;
    public String exerciseName;

    MeterView setPicker;
    MeterView repPicker;

    int sets = 0,reps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_info);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myReff = mFirebaseDatabase.getReference("Values");

        user = mAuth.getCurrentUser();
        userID = user.getUid();

        ImageView gifView = findViewById(R.id.gifView);
        TextView exerciseTitle = findViewById(R.id.exerciseName);
        TextView difficulty = findViewById(R.id.difficulty);
        Button addToListButton = findViewById(R.id.addToListButton);

        Bundle bundle = getIntent().getExtras();
        exerciseId = bundle.getString("id");
        exerciseName = bundle.getString("name");
        exerciseDesc = bundle.getString("desc");

        setPicker = findViewById(R.id.meterViewSets);
        repPicker = findViewById(R.id.meterViewReps);

        int drawableId = getResources().getIdentifier(exerciseId, "raw", getPackageName());

        Glide.with(this)
                .load(drawableId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(gifView);

        exerciseTitle.setText(exerciseName);
        difficulty.setText(exerciseDesc);

        addToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reps = repPicker.getValue();
                sets = setPicker.getValue();

                if(reps != 0 && sets != 0){
                    myReff.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(exerciseId)){
                                Toast.makeText(exerciseInfo.this,"This exercise is already in the list",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    myReff.child(userID).child("exerciseList").child(exerciseId).child("exerciseName")
                                            .setValue(exerciseName);
                                    myReff.child(userID).child("exerciseList").child(exerciseId).child("sets")
                                            .setValue(sets);
                                    myReff.child(userID).child("exerciseList").child(exerciseId).child("reps")
                                            .setValue(reps);
                                }
                                catch (Exception e){
                                    Toast.makeText(exerciseInfo.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                                finally {
                                    Toast.makeText(exerciseInfo.this, "Exercise added to the list", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    Toast.makeText(exerciseInfo.this, "Please select sets and reps", Toast.LENGTH_SHORT).show();
                }




                /*Boolean emptyList = false;
                MyLists myLists = new MyLists();
                if (myLists == null){
                    emptyList = true;
                } else {
                    emptyList = false;
                }

                if(myRef.child(userID).child("lists") == null){

                }

                AlertDialog.Builder ab = new AlertDialog.Builder(exerciseInfo.this);
                ab.setTitle("Select Sets and Reps");

                TextView info = new TextView(exerciseInfo.this);

                if(!emptyList){
                    info.setText("Empty List");
                    ab.setView(info);
                }

                ab.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog a = ab.create();
                a.show();
                */
            }
        });


    }
}
