package com.example.eventbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProposeEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText EName,ELink,EDate,ELocation;
    Button ESubmit;
    FirebaseFirestore fStore;
    String EEventid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_event);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.features,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        ImageButton home  = findViewById(R.id.imageButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProposeEvent.this,MainActivity.class));
            }
        });


        EditText EName = findViewById(R.id.event_name);
        EditText ELink = findViewById(R.id.event_link);
        EditText EDate = findViewById(R.id.event_date);
        EditText ELocation = findViewById(R.id.location);
        Button ESubmit = findViewById(R.id.button_form);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        ESubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EName.getText().toString().trim();
                String link = ELink.getText().toString().trim();
                String date = EDate.getText().toString().trim();
                String location = ELocation.getText().toString().trim();


                //add event
                DocumentReference documentReference = fStore.collection("events").document();
                Map<String,Object> event = new HashMap<>();
                event.put("name",name);
                event.put("link",link);
                event.put("date",date);
                event.put("location",location);

                documentReference.set(event)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ProposeEvent.this,"Event request sent",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Events.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProposeEvent.this,"Failed sending the request. You must be loged in",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Login.class));
                            }
                        });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();


        if(text.equals("Register"))
        {
            startActivity(new Intent(ProposeEvent.this, RegisterAct.class));
        }else if(text.equals("Events")){
            startActivity(new Intent(ProposeEvent.this,Events.class));
        }else if(text.equals("Profile")){
            startActivity(new Intent(ProposeEvent.this,Profile.class));
        }
        else if(text.equals("Login")) {
             startActivity(new Intent(ProposeEvent.this, Login.class));
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}