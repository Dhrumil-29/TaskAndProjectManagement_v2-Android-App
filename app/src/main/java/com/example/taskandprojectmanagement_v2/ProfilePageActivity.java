package com.example.taskandprojectmanagement_v2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfilePageActivity extends AppCompatActivity {

    Dialog dialog;
    TextInputEditText userName;
    TextInputEditText fullName;
    TextInputEditText DOB;
    TextInputEditText email;
    TextInputEditText phoneNumber;
    TextInputEditText Role;
    final Calendar myCalendar = Calendar.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_page);


        Button button = findViewById(R.id.profileDone);
        userName = findViewById(R.id.userName);
        fullName = findViewById(R.id.fullName);
        DOB = findViewById(R.id.DOB);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        Role = findViewById(R.id.role);


        //Date Picker Dialog Box logic
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ProfilePageActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an intent
                showDialog();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/YY";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        DOB.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void showDialog() {
        dialog = new Dialog(ProfilePageActivity.this);
        dialog.setContentView(R.layout.congratulation_dialog_box);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation;
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btn = dialog.findViewById(R.id.GoToHome);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ProfilePageActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
    }
}