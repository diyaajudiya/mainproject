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
import com.example.foodfest.models.Restaurantmodel;

import java.util.List;

public class Menulistadapter extends RecyclerView.Adapter<Menulistadapter.Myviewholder> {

    private List<Menu> menuList;


    private  MenuListClickListener restaurantListclickListener;
    public Menulistadapter(List<Menu> menuList , MenuListClickListener restaurantListclickListener){
        this.menuList =menuList;
    }
    public  void updateData(List<Menu> menuList){
        this.menuList =menuList;
        this.restaurantListclickListener= restaurantListclickListener;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Menulistadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_recycle_row,parent,false );
        return new Myviewholder(view);
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.MenuName.setText(menuList.get(position).getItem());
        holder.MenuPrice.setText( "Price: $"+menuList.get(position).getprice());
        holder.addToCardButton.setOnClickListener(v -> {
            Menu menu = menuList.get(position);
            menu.setTotalInCart(1);
            restaurantListclickListener.onAddToCardClick(menu);
            holder.AddMoreLayout.setVerticalGravity(View.VISIBLE);
            holder.addToCardButton.setVisibility(View.GONE);
            holder.Tvcount.setTag(menu.getTotalInCart()+"");
        });

        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu = menuList.get(position);
                int total = menu.getTotalInCard();
                total--;
                if(total>0){
                    menu.setTotalInCart(total);
                    restaurantListclickListener.onUpdateCardClick(menu);
                    holder.Tvcount.setTag( total +"");
                }else {
                    holder.AddMoreLayout.setVisibility(View.GONE);
                    holder.addToCardButton.setVisibility(View.VISIBLE);
                    menu.setTotalInCart(total);
                    restaurantListclickListener.onRemoveFromCartClick(menu);
                }



            }
        });


        holder.imageAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu = menuList.get(position);
                int total = menu.getTotalInCard();
                total++;
                if(total<=10 ){
                    menu.setTotalInCart(total);
                    restaurantListclickListener.onUpdateCardClick(menu);
                    holder.Tvcount.setTag( total +"");
                }



            }
        });


        Glide.with(holder.thamimage)
                .load(menuList.get(position).equals(R.raw.restaurant)).into(holder.thamimage);

    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }
    static class Myviewholder extends RecyclerView.ViewHolder{
        TextView MenuName;
        TextView MenuPrice;
        TextView addToCardButton;
        ImageView thamimage;
        ImageView imageMinus;
        ImageView Tvcount;
        ImageView imageAddOne;
        LinearLayout AddMoreLayout;



        public Myviewholder(View view){
        super(view);
            MenuName = view.findViewById(R.id.Menuname);
            MenuPrice = view.findViewById(R.id.MenuPrice);
            addToCardButton = view.findViewById(R.id.addToCardButton);
        thamimage = view.findViewById(R.id.thamimage);
            imageMinus = view.findViewById(R.id.imageMinus);
            Tvcount = view.findViewById(R.id.Tvcount);
            imageAddOne = view.findViewById(R.id. imageAddOne);
            AddMoreLayout = view.findViewById(R.id. AddMoreLayout);
        }
    }

    public interface MenuListClickListener{
        public void  onAddToCardClick(Menu Menu);
        public void  onUpdateCardClick(Menu Menu);
        public void  onRemoveFromCartClick(Menu Menu);
    }

    private class Menu_recycle_row {
    }
}

