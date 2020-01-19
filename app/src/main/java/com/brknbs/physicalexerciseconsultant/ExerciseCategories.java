package com.brknbs.physicalexerciseconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ExerciseCategories extends AppCompatActivity implements View.OnClickListener {

    ImageButton neck,back,elbow,shoulder,knee,ankle,wrist,hip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_categories);

        neck = findViewById(R.id.neck);
        back = findViewById(R.id.back);
        elbow = findViewById(R.id.elbow);
        shoulder = findViewById(R.id.shoulder);
        knee = findViewById(R.id.knee);
        ankle = findViewById(R.id.ankle);
        wrist = findViewById(R.id.wrist);
        hip = findViewById(R.id.hip);

        neck.setOnClickListener(this);
        back.setOnClickListener(this);
        elbow.setOnClickListener(this);
        shoulder.setOnClickListener(this);
        knee.setOnClickListener(this);
        ankle.setOnClickListener(this);
        wrist.setOnClickListener(this);
        hip.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.neck :
                startActivity(new Intent(this,NeckExercises.class));
                break;
            case R.id.back :
                startActivity(new Intent(this,BackExercises.class));
                break;
            case R.id.elbow :
                startActivity(new Intent(this,ElbowExercises.class));
                break;
            case R.id.shoulder :
                startActivity(new Intent(this,ShoulderExercises.class));
                break;
            case R.id.knee :
                startActivity(new Intent(this,KneeExercises.class));
                break;
            case R.id.ankle :
                startActivity(new Intent(this,AnkleExercises.class));
                break;
            case R.id.wrist :
                startActivity(new Intent(this,WristExercises.class));
                break;
            case R.id.hip :
                startActivity(new Intent(this,HipExercises.class));
                break;
        }
    }
}
