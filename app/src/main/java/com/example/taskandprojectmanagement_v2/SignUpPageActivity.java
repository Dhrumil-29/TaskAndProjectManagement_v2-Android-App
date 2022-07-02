package com.example.taskandprojectmanagement_v2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class SignUpPageActivity extends AppCompatActivity {

    TextInputEditText mEmail, mPassword;
    Button button;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_page);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        button = findViewById(R.id.signUpButton);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.loading);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //for the error messages
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required...");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpPageActivity.this, "Password is Required...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(SignUpPageActivity.this, "Password Must be >= 6 Characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpPageActivity.this, "SignUp Successfully...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ProfilePageActivity.class));
                            progressBar.setVisibility(View.GONE);
                            finish();
                        } else {
                            Toast.makeText(SignUpPageActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }

    public void SignInWithFacebook(View view) {
        //create an intent
        Intent intent = new Intent(this, ProfilePageActivity.class);
        startActivity(intent);
        finish();
    }

    public void SignInWithGoogle(View view) {
        //create an intent
        Intent intent = new Intent(this, ProfilePageActivity.class);
        startActivity(intent);
        finish();
    }

    public void SignInWithApple(View view) {
        //create an intent
        Intent intent = new Intent(this, ProfilePageActivity.class);
        startActivity(intent);
        finish();
    }

    public void SignIn(View view) {
        //create an intent
        Intent intent = new Intent(this, SignInPageActivity.class);
        startActivity(intent);
    }
}