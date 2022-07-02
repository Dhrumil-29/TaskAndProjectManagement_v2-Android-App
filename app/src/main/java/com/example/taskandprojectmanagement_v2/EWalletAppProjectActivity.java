package com.example.taskandprojectmanagement_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EWalletAppProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewallet_app_project);

        TextView topMenuBar = findViewById(R.id.TopMenuBar);
        TextView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EWalletAppProjectActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        topMenuBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),topMenuBar);
                popupMenu.inflate(R.menu.top_menu);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.addCover:
                                Toast.makeText(EWalletAppProjectActivity.this, "Clicked Add Cover", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.addLogo:
                                Toast.makeText(EWalletAppProjectActivity.this, "Clicked Add Logo", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.setColor:
                                Toast.makeText(EWalletAppProjectActivity.this, "Clicked Set Color", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.editProfile:
                                Toast.makeText(EWalletAppProjectActivity.this, "Clicked Edit Profile", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.deleteProject:
                                Toast.makeText(EWalletAppProjectActivity.this, "Clicked Delete Project", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
    }
}