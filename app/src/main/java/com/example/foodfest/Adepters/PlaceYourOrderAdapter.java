package com.example.foodfest.Adepters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodfest.R;

import java.util.List;

public class PlaceYourOrderAdapter extends RecyclerView.Adapter<PlaceYourOrderAdapter.Myviewholder> {

    private List<Menu> menuList;



    public PlaceYourOrderAdapter(List<Menu> menuList ) {
        this.menuList =menuList;
    }
    public  void updateData(List<Menu> menuList){
        this.menuList =menuList;

        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public PlaceYourOrderAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_recycle_row,parent,false );
        return new Myviewholder(view);
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.MenuName.setText(menuList.get(position).getItem());
        holder.MenuPrice.setText( "Price: $"+String.format("%2f",menuList.get(position).getPrice()*menuList.get(position).getTotalInCard()));

        holder.MenuQty.setText("Qty" + menuList.get(position).getTotalInCart());


        Glide.with(holder.thamimage)
                .load(menuList.get(position).getUrl()).into(holder.thamimage);

    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }
    static class Myviewholder extends RecyclerView.ViewHolder{
        TextView MenuName;
        TextView MenuPrice;
        TextView MenuQty;
        ImageView thamimage;



        public Myviewholder(View view){
        super(view);
            MenuName = view.findViewById(R.id.Menuname);
            MenuPrice = view.findViewById(R.id.MenuPrice);
            MenuQty = view.findViewById(R.id.MenuQty);
        thamimage = view.findViewById(R.id.thamimage);

        }
    }



}

