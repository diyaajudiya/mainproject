package com.example.foodfest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodfest.Adepters.Menulistadapter;
import com.example.foodfest.models.Restaurantmodel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantmenuActivity extends AppCompatActivity implements Menulistadapter.MenuListClickListener {
     private List<Menu> MenuList = null;
    private Menulistadapter menulistadapter;
    private  List<Menu> itemsInCartList;
    private int totalItemInCart = 0;
    private TextView buttonCheckout;
    Button btn;
    View lyout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantmenu);
        btn = findViewById(R.id.visi);
        lyout = findViewById(R.id.SHOW);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lyout.setVisibility(View.VISIBLE);

            }
        });

        Restaurantmodel restaurantmodel = getIntent().getParcelableExtra("RestaurantModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurantmodel.getName());
        actionBar.setSubtitle(restaurantmodel.getAddrass());
        actionBar.setDisplayHomeAsUpEnabled(true);


        MenuList = restaurantmodel.getMenus();
        initrecyclerview();

         buttonCheckout =  findViewById(R.id.buttonCheckout);
         buttonCheckout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (itemsInCartList != null && itemsInCartList.size() <= 0 ){
                     Toast.makeText(RestaurantmenuActivity.this, "Please add some item in card.", Toast.LENGTH_SHORT).show();
                     return;

                 }
                 restaurantmodel.setMenus(itemsInCartList);
                 Intent intent = new Intent(RestaurantmenuActivity.this, PlaceYourOrderActivity.class);
                 intent.putExtra("RestaurantModel",restaurantmodel);
                 startActivityForResult(intent,1000);
             }
         });
    }

    private void initrecyclerview(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        menulistadapter=new Menulistadapter(MenuList ,this);
        recyclerView.setAdapter(menulistadapter);

    }

    @Override
    public void onAddToCardClick(Menu Menu) {
        if (itemsInCartList ==null);{
            itemsInCartList= new ArrayList<>();
        }
        itemsInCartList.add(Menu);
        totalItemInCart = 0;
        for (Menu m :itemsInCartList){
            totalItemInCart = totalItemInCart + Menu.getTotalInCart();
        }
        buttonCheckout.setText("Checkout("+totalItemInCart+") items");

    }

    @Override
    public void onUpdateCardClick(Menu Menu) {
        if (itemsInCartList.contains(Menu)){
            int index = itemsInCartList.indexOf(Menu);
            itemsInCartList.remove(index);
            itemsInCartList.add(index,Menu);

            totalItemInCart = 0;

            for (Menu m :itemsInCartList){
                totalItemInCart = totalItemInCart + Menu.getTotalInCart();
            }
            buttonCheckout.setText("Checkout("+totalItemInCart+") items");
        }

    }

    @Override
    public void onRemoveFromCartClick(Menu Menu) {
        if (itemsInCartList.contains(Menu)){
            itemsInCartList.remove(Menu);

            for (Menu m :itemsInCartList){
                totalItemInCart = totalItemInCart + Menu.getTotaInCart();
            }
            buttonCheckout.setText("Checkout("+totalItemInCart+") items");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK){
            //
            finish();
        }
    }
}