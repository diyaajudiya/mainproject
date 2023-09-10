package com.example.foodfest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodfest.Adepters.PlaceYourOrderAdapter;
import com.example.foodfest.models.Restaurantmodel;

public class PlaceYourOrderActivity extends AppCompatActivity {

    private EditText inputName , inputAddress,inputcity,inputstate,inputzip,inputcardnumber,inputcardExpiry,inputcardpin;
    private RecyclerView carditemrecycleview;
    private TextView tvsubtotalAmount,tvdeliverychargeAmount,Tvdeliverycharge,tvtotalamount,buttonplaceyourorder;
    private SwitchCompat swichDelivery;
    private boolean inDeliveryOn;
    private PlaceYourOrderAdapter placeYourOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_youre_order);

        Restaurantmodel restaurantmodel = getIntent().getParcelableExtra("RestaurantModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurantmodel.getName());
        actionBar.setSubtitle(restaurantmodel.getAddrass());
        actionBar.setDisplayHomeAsUpEnabled(true);


        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        inputcity = findViewById(R.id.inputcity);
        inputstate = findViewById(R.id.inputstate);
        inputzip = findViewById(R.id.inputzip);
        inputcardnumber = findViewById(R.id.inputcardnumber);
        inputcardExpiry = findViewById(R.id.inputcardExpiry);
        inputcardpin = findViewById(R.id.inputcardpin);
        carditemrecycleview = findViewById(R.id.carditemrecycleview);
        tvsubtotalAmount = findViewById(R.id.tvsubtotalAmount);
        tvdeliverychargeAmount = findViewById(R.id.tvdeliverychargeAmount);
        Tvdeliverycharge = findViewById(R.id.tvdeliverycharge);
        tvtotalamount = findViewById(R.id.tvtotalamount);
        buttonplaceyourorder = findViewById(R.id.buttonplaceyourorder);
        swichDelivery = findViewById(R.id.swichDelivery);
        buttonplaceyourorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onplaceOrderButtonClick(restaurantmodel);
            }

            private void onplaceOrderButtonClick(Restaurantmodel restaurantmodel) {

            }
        });
        swichDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputAddress.setVisibility(View.VISIBLE);
                    inputcity.setVisibility(View.VISIBLE);
                    inputstate.setVisibility(View.VISIBLE);
                    inputcardpin.setVisibility(View.VISIBLE);
                    tvdeliverychargeAmount.setVisibility(View.VISIBLE);
                    Tvdeliverycharge.setVisibility(View.VISIBLE);
                    inDeliveryOn=true;
                } else {
                    inputAddress.setVisibility(View.GONE);
                    inputcity.setVisibility(View.GONE);
                    inputstate.setVisibility(View.GONE);
                    inputcardpin.setVisibility(View.GONE);
                    tvdeliverychargeAmount.setVisibility(View.GONE);
                    Tvdeliverycharge.setVisibility(View.GONE);
                    inDeliveryOn=false;
                    calculateTotalAmount(restaurantmodel);
                }
            }
        });
        initRecyclerview(restaurantmodel);
    }
    private  void calculateTotalAmount( Restaurantmodel restaurantmodel){
        float subTotalAmount = 0f;
        for (Menu menu:restaurantmodel.getMenus()){
            subTotalAmount += Menu.getPrice()* Menu.getTotalInCard();
        }
        tvsubtotalAmount.setText("$"+String.format("%.2f",subTotalAmount));
        if (inDeliveryOn){
            tvdeliverychargeAmount.setText("$"+String.format("%.2f",restaurantmodel.getDelivery_charge()));
            subTotalAmount += restaurantmodel.getDelivery_charge();
        }
        tvtotalamount.setText("$"+String.format("%.2f",subTotalAmount));

    }

    private void onplaceOrderButtonClick(Restaurantmodel restaurantmodel){
        if (TextUtils.isEmpty(inputName.getText().toString())){
            inputName.setError("Please enter name");
            return;
        } else if  (inDeliveryOn && TextUtils.isEmpty(inputAddress.getText().toString())){
            inputAddress.setError("Please enter Address");
            return;
        }else if  (inDeliveryOn && TextUtils.isEmpty(inputstate.getText().toString())){
            inputstate.setError("Please enter state");
            return;
        }else if  (inDeliveryOn && TextUtils.isEmpty(inputzip.getText().toString())){
            inputzip.setError("Please enter pin");
            return;
        }else if  ( TextUtils.isEmpty(inputcardnumber.getText().toString())){
            inputcardnumber.setError("Please enter card number");
            return;
        }else if  ( TextUtils.isEmpty(inputcardExpiry.getText().toString())){
            inputcardExpiry.setError("Please enter card number");
            return;
        }else if  ( TextUtils.isEmpty(inputcardpin.getText().toString())){
            inputcardpin.setError("Please enter card number");
            return;
        }
        //start success activity..
        Intent intent=new Intent(PlaceYourOrderActivity.this,orderSucceessActivity.class);
        intent.putExtra("RestaurantModel",restaurantmodel);
        startActivityForResult(intent,1000);

    }
    private void initRecyclerview(Restaurantmodel restaurantmodel){
        carditemrecycleview.setLayoutManager(new LinearLayoutManager(this));
        placeYourOrderAdapter = new PlaceYourOrderAdapter(restaurantmodel.getMenus());

        carditemrecycleview.setAdapter(placeYourOrderAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         if(requestCode==1000){
             setResult(Activity.RESULT_OK);
             finish();
         }
        super.onActivityResult(requestCode, resultCode, data);
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
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}