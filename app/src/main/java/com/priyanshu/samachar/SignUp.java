package com.priyanshu.samachar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignUp extends AppCompatActivity {
    TextInputLayout memail,mpassword;
    Button msignup;
    LinearProgressIndicator mprogress;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        msignup = findViewById(R.id.sign_up);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mprogress = findViewById(R.id.progressbar);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();

        msignup.setOnClickListener(view -> {
            String femail = Objects.requireNonNull(memail.getEditText()).getText().toString();
            String fpassword = Objects.requireNonNull(mpassword.getEditText()).getText().toString();
            mprogress.setVisibility(View.VISIBLE);
            if (femail.isEmpty() ||fpassword.isEmpty()) {
                Toast.makeText(SignUp.this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
                mprogress.setVisibility(View.INVISIBLE);
            }
            else
            {
                fAuth.createUserWithEmailAndPassword(femail, fpassword)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                mprogress.setVisibility(View.INVISIBLE);
                                Toast.makeText(SignUp.this, "Sign In Sucess", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this,MainActivity.class);
                                SignUp.this.startActivity(intent);
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                               // tv1.setVisibility(View.VISIBLE)
                                mprogress.setVisibility(View.INVISIBLE);
                                Toast.makeText(SignUp.this, "Error Please Try Again Later", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }
}