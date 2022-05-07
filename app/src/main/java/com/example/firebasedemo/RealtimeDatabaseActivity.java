package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasedemo.adapter.RealtimeDatabaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RealtimeDatabaseActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_database);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("test");

        RecyclerView recycler = findViewById(R.id.recycler);
        RealtimeDatabaseRecyclerAdapter realtimeDatabaseRecyclerAdapter
                = new RealtimeDatabaseRecyclerAdapter(this);
        recycler.setAdapter(realtimeDatabaseRecyclerAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        Button btnAdd = findViewById(R.id.btn_add);
        EditText edt = findViewById(R.id.edt);
        btnAdd.setOnClickListener(v -> {
            String txt = edt.getText().toString();
            myRef.push().setValue(txt).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(RealtimeDatabaseActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Log.d("firebase", "success");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RealtimeDatabaseActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    Log.d("firebase", "fail");
                }
            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("firebase", "complete");
                }
            });
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> lst = new ArrayList<>();
                for(DataSnapshot child : snapshot.getChildren()){
                    lst.add(child.getValue(String.class));
                }
                realtimeDatabaseRecyclerAdapter.setLst(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "cancel");
            }
        });



    }
}