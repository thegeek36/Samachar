package com.priyanshu.samachar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Register extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://samachar-eb25d-default-rtdb.firebaseio.com/");
    TextInputLayout mname, mphone, memail, mpassword;
    Button mregister;
    LinearProgressIndicator mprogress;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mname = findViewById(R.id.name);
        memail = findViewById(R.id.email);
        mphone = findViewById(R.id.phone);
        mpassword = findViewById(R.id.password);
        mregister = findViewById(R.id.register_Button);
        mprogress = findViewById(R.id.progressbar);
        fAuth = FirebaseAuth.getInstance();
        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get data from user
                String femail = Objects.requireNonNull(memail.getEditText()).getText().toString();
                String fpassword = Objects.requireNonNull(mpassword.getEditText()).getText().toString();
                String fname = Objects.requireNonNull(mname.getEditText()).getText().toString();
                String fnumber = Objects.requireNonNull(mphone.getEditText()).getText().toString();

                //check if user has filled all fields
                if (femail.isEmpty() || fname.isEmpty() || fnumber.isEmpty() || fpassword.isEmpty()) {
                    Toast.makeText(Register.this, "Please all all Fields", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(femail)) {
                                Toast.makeText(Register.this, "Email Already Registered", Toast.LENGTH_SHORT).show();
                            } else {
                                mprogress.setVisibility(View.VISIBLE);
                                //Sending Data to database & Here we have kept Email as unique value for each dataset
                                databaseReference.child("users").child(femail).child("EMail").setValue(femail);
                                databaseReference.child("users").child(femail).child("Password").setValue(fpassword);
                                databaseReference.child("users").child(femail).child("Name").setValue(fname);
                                databaseReference.child("users").child(femail).child("Number").setValue(fnumber);
                                //show a sucess message
                                Toast.makeText(Register.this, "User Registered Sucessfully", Toast.LENGTH_SHORT).show();
                                Intent myIntent = new Intent(Register.this,Login.class);
                                startActivity(myIntent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Register.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}

