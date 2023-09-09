package com.example.foodfest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sing_in extends AppCompatActivity {

    private Button sing_in;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        sing_in = findViewById(R.id.singin);


        sing_in . setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(sing_in.this, "Register has been clicked ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(sing_in.this , MainActivity2.class);
                startActivity(intent);




            }
        });

    }
}