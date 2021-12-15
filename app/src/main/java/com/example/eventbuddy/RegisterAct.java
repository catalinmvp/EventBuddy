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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.features,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        ImageButton home  = findViewById(R.id.imageButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterAct.this,MainActivity.class));
            }
        });



        TextView login = findViewById(R.id.textView2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterAct.this,Login.class));
            }
        });



        Button register  = findViewById(R.id.button2);
        EditText mName = findViewById(R.id.name);
        EditText mEmail = findViewById(R.id.email);
        EditText mPassword = findViewById(R.id.pass);
        EditText mRePassword = findViewById(R.id.repass);
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        ProgressBar progressBar = findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Events.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String repassword = mRePassword.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    mName.setError("Name section wasn't completed");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email section wasn't completed");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password section wasn't completed");
                    return;
                }

                if(TextUtils.isEmpty(repassword)){
                    mRePassword.setError("Repeat password section wasn't completed");
                    return;
                }

                if(password.length() < 8){
                    mPassword.setError("Password Must Contain at least 8 Characters");
                    return;
                }

                if(password.equalsIgnoreCase(repassword)){
                    mRePassword.setText("Password matches");
                }else{
                    mRePassword.setError("Passwords don't match");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ( task.isSuccessful()){
                            Toast.makeText(RegisterAct.this,"User created!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Events.class));
                        }
                        else{
                            Toast.makeText(RegisterAct.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();


       if(text.equals("Events"))
       {
            startActivity(new Intent(RegisterAct.this,Events.class));
        }else if(text.equals("Profile")){
            startActivity(new Intent(RegisterAct.this,Profile.class));
        }else if(text.equals("Propose Event")){
            startActivity(new Intent(RegisterAct.this,ProposeEvent.class));
        }else if(text.equals("Login")){
             startActivity(new Intent(RegisterAct.this, Login.class));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
