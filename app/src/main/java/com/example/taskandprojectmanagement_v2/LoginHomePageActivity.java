package com.example.taskandprojectmanagement_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginHomePageActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_home_page);


        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
            finish();
        }
    }


    public void SignInWithFacebook(View view) {
        //create an intent
        Intent intent = new Intent(LoginHomePageActivity.this, ProfilePageActivity.class);
        startActivity(intent);
        finish();
    }

    public void SignInWithGoogle(View view) {
        //create an intent
        Intent intent = new Intent(LoginHomePageActivity.this, ProfilePageActivity.class);
        startActivity(intent);
        finish();
    }

    public void SignInWithApple(View view) {
        //create an intent
        Intent intent = new Intent(LoginHomePageActivity.this, ProfilePageActivity.class);
        startActivity(intent);
        finish();
    }

    public void SignIn(View view) {
        //create an intent
        Intent intent = new Intent(LoginHomePageActivity.this, SignInPageActivity.class);
        startActivity(intent);
    }

    public void SignUp(View view) {
        //create an intent
        Intent intent = new Intent(LoginHomePageActivity.this, SignUpPageActivity.class);
        startActivity(intent);
    }
}