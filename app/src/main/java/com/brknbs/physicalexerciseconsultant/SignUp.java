package com.brknbs.physicalexerciseconsultant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button signUpButton;
    EditText emailSignUp,passwordSignUp,nameSignUp;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    String name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameSignUp = findViewById(R.id.nameSignUp);
        emailSignUp = findViewById(R.id.emailSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);

        signUpButton = findViewById(R.id.signUpButton);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");

        signUpButton.setOnClickListener(this);
    }

    private void registerUser(){
        email = emailSignUp.getText().toString().trim();
        password = passwordSignUp.getText().toString().trim();
        name = nameSignUp.getText().toString().trim();

        if(email.isEmpty()){
            emailSignUp.setError("Email is required");
            emailSignUp.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailSignUp.setError("Please enter a valid email");
            emailSignUp.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passwordSignUp.setError("Password is required");
            passwordSignUp.requestFocus();
            return;
        }

        if(password.length() < 6){
            passwordSignUp.setError("Minimum lenght of password should be 6");
            passwordSignUp.requestFocus();
            return;
        }

        if(name == ""){
            nameSignUp.setError("Please enter your name");
            nameSignUp.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();

                    myRef.child(userID).child("Name").setValue(name);
                    myRef.child(userID).child("E-Mail").setValue(email);
                    myRef.child(userID).child("ID").setValue(userID);

                    Toast.makeText(getApplicationContext(),"User registered successfully",Toast.LENGTH_SHORT).show();
                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButton :
                registerUser();
                break;
            case R.id.toSignUp :
                finish();
                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}
