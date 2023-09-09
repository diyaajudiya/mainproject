package com.example.foodfest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodfest.models.Restaurantmodel;
import com.example.foodfest.Adepters.restaurantlistadapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements restaurantlistadapter.RestaurantListclickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Restaurant List");

        List<Restaurantmodel> restaurantmodels= getRestaurantData();

        initRecyclerView(restaurantmodels);
    }

    private void initRecyclerView(List<Restaurantmodel>restaurantmodelList){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantlistadapter adepter = new restaurantlistadapter(restaurantmodelList,this);
        recyclerView.setAdapter(adepter);
    }

    private List<Restaurantmodel> getRestaurantData() {
        InputStream is = getResources().openRawResource(R.raw.restaurant);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {

            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

        } catch (Exception e) {

        }
        String jsonStr = writer.toString();
        Gson gson = new Gson();
        Restaurantmodel[] restaurantmodels = gson.fromJson(jsonStr, Restaurantmodel[].class);
        List<Restaurantmodel> restList = Arrays.asList(restaurantmodels);

        return restList;
    }

    @Override
    public void onImageClick(Restaurantmodel restaurantmodel) {
        Intent intent =  new Intent(MainActivity2.this,RestaurantmenuActivity.class);


        intent.putExtra("RestaurantModel",restaurantmodel);
        startActivity(intent);
    }
}