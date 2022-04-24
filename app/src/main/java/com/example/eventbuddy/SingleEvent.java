package com.example.eventbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SingleEvent extends AppCompatActivity {
    TextView name,date,location,link;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        Intent i = getIntent();

        name = findViewById(R.id.EventTitle);
        date = findViewById(R.id.EventDate);
        location = findViewById(R.id.EventLocation);
        //link = findViewById(R.id.EventLink);
        //bundle pt a aduce ino
        //graddle pt imagini hashed
        //afiseaza mai trb sa aduc datele din stanga in dreapta + glide pt poze
    }
}