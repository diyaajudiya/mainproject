package com.example.foodfest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class singup extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextInputEditText Edittextpassword,Edittextemail;
    Button button;
    TextView textView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        mAuth = FirebaseAuth.getInstance();
        Edittextemail = findViewById(R.id.editTextTextEmail);
        Edittextpassword = findViewById(R.id.editTextTextPassword);
        button = findViewById(R.id.signup);
        textView = findViewById(R.id.alredyhave);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),singup.class);
                startActivity(intent);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email , password;
                email = String.valueOf( Edittextemail.getText().toString());
                password = String.valueOf(Edittextpassword.getText().toString());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(singup.this, "its impty fill firs or correctly", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(singup.this, "its impty fill firs or correctly", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(singup.this, "Congratulation You added in food family",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(singup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }


}