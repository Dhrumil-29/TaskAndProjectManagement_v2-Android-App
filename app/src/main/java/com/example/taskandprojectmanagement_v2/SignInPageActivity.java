package com.example.taskandprojectmanagement_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class SignInPageActivity extends AppCompatActivity {

    TextInputEditText mEmail, mPassword;
    Button button;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in_page);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        button = findViewById(R.id.signIpButton);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.loading);
        forgetPassword = findViewById(R.id.forgetPassword);

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
                    Toast.makeText(SignInPageActivity.this, "Password is Required...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(SignInPageActivity.this, "Password Must be >= 6 Characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInPageActivity.this, "Log In Successfully...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                            progressBar.setVisibility(View.GONE);
                            finish();
                        } else {
                            Toast.makeText(SignInPageActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("forgot Password pressed...");
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Receive Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SignInPageActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(SignInPageActivity.this, "Error ! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Close Dialog Box
                    }
                });

                passwordResetDialog.create().show();
                System.out.println("forgot Password pressed overr...");
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

    public void SignUp(View view) {
        Intent intent = new Intent(this, SignUpPageActivity.class);
        startActivity(intent);
        finish();
    }
}