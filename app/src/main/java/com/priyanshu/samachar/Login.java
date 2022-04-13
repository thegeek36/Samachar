package com.priyanshu.samachar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {
    TextInputLayout memail,mpassword;
    Button mlogin;
    LinearProgressIndicator mprogress;
    FirebaseAuth fAuth;
    TextView forget_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mlogin = findViewById(R.id.log_in);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mprogress = findViewById(R.id.progressbar);
        forget_pass = findViewById(R.id.forgot_pass);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(Login.this,MainActivity.class);
            Login.this.startActivity(intent);
            finish();
        }
        final Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Login.this,SignUp.class);
                startActivity(myIntent);

            }
        });
        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String femail = Objects.requireNonNull(memail.getEditText()).getText().toString();
                if (femail.isEmpty()) {
                    Toast.makeText(Login.this,"Enter Email",Toast.LENGTH_SHORT).show();
                }
                else{
                fAuth.sendPasswordResetEmail(femail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Login.this, "Error Sending Mail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            }
        });

        mlogin.setOnClickListener(view -> {
            String femail = Objects.requireNonNull(memail.getEditText()).getText().toString();
            String fpassword = Objects.requireNonNull(mpassword.getEditText()).getText().toString();
            mprogress.setVisibility(View.VISIBLE);
            if (femail.isEmpty() ||fpassword.isEmpty()) {
                Toast.makeText(Login.this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
                mprogress.setVisibility(View.INVISIBLE);
            }
            else
            {
                fAuth.signInWithEmailAndPassword(femail, fpassword)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    mprogress.setVisibility(View.INVISIBLE);
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    Login.this.startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Login.this,"Error "+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                                    mprogress.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
            }
        });

    }
}