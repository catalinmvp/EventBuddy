package com.example.eventbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView forgotText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.features,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        ImageButton home  = findViewById(R.id.imageButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });



        TextView register = findViewById(R.id.textView2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,RegisterAct.class));
            }
        });


        Button login = findViewById(R.id.button2);
        EditText mEmail = findViewById(R.id.email);
        EditText mPassword = findViewById(R.id.pass);
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        forgotText = findViewById(R.id.pass_forgot);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     String email = mEmail.getText().toString().trim();
                     String password = mPassword.getText().toString().trim();



                     // user validation
                     if(TextUtils.isEmpty(email)){
                         mEmail.setError("Email is required");
                        return;
                     }

                     if(TextUtils.isEmpty(password)){
                         mPassword.setError("Password is required");
                         return;
                     }

                     fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Login.this,"Login succesful",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Events.class));
                                }else {
                                    Toast.makeText(Login.this,"Bad Credentials" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                         }
                     });



            }
        });

        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter mail to receive reset link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
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
            startActivity(new Intent(Login.this,Events.class));
        }else if(text.equals("Profile")){
            startActivity(new Intent(Login.this,Profile.class));
        }else if(text.equals("Propose Event")){
            startActivity(new Intent(Login.this,ProposeEvent.class));
        }else if(text.equals("Register")){
            startActivity(new Intent(Login.this,ProposeEvent.class));
        }else if(text.equals("Login")){
             startActivity(new Intent(Login.this, Login.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}