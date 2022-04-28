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

import java.util.ArrayList;

public class SingleEvent extends AppCompatActivity {
    TextView name,date,location,link;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        //final String title = (String) bundle.getSerializable("events");
        String name2 = bundle.getString("name");
        String date2 = bundle.getString("date");
        String location2 = bundle.getString("location");
        String link2 = bundle.getString("link");

        name = findViewById(R.id.EventTitle);
        date = findViewById(R.id.EventDate);
        location = findViewById(R.id.EventLocation);
        link = findViewById(R.id.EventLink);


        name.setText(name2);
        link.setText(link2);
        date.setText(date2);
        location.setText(location2);

        //graddle pt imagini hashed
        //afiseaza mai trb sa aduc datele din stanga in dreapta + glide pt poze
    }
}