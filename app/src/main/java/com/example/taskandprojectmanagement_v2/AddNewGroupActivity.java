package com.example.taskandprojectmanagement_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddNewGroupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_group);

        TextView createGroupChat = findViewById(R.id.createGroupChat);

        createGroupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewGroupActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
