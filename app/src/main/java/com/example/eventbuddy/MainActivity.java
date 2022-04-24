package com.example.eventbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.features,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        ImageButton home  = findViewById(R.id.imageButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();


        if(text.equals("Register"))
        {
            startActivity(new Intent(MainActivity.this, RegisterAct.class));
        }else if(text.equals("Events")){
            startActivity(new Intent(MainActivity.this,Events.class));
        }else if(text.equals("Profile")){
            startActivity(new Intent(MainActivity.this,Profile.class));
        }else if(text.equals("Propose Event")){
            startActivity(new Intent(MainActivity.this,ProposeEvent.class));
        }else if(text.equals("Login")){
            startActivity(new Intent(MainActivity.this, Login.class));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}