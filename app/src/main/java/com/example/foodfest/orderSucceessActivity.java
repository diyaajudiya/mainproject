package com.example.foodfest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.foodfest.models.Restaurantmodel;

public class orderSucceessActivity extends AppCompatActivity {

    public TextView buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_succeess);

        Restaurantmodel restaurantmodel = getIntent().getParcelableExtra("RestaurantModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurantmodel.getName());
        actionBar.setSubtitle(restaurantmodel.getAddrass());
        actionBar.setDisplayHomeAsUpEnabled(false);

        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}