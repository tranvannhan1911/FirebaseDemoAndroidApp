package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRealtimeDB = findViewById(R.id.btn_realtime_database);
        btnRealtimeDB.setOnClickListener(v -> {
            Intent intent = new Intent(this, RealtimeDatabaseActivity.class);
            startActivity(intent);
        });

        Button btnAuthenticate = findViewById(R.id.btn_authenticate);
        btnAuthenticate.setOnClickListener(v -> {
            Intent intent = new Intent(this, AuthenticationActivity.class);
            startActivity(intent);
        });
    }
}