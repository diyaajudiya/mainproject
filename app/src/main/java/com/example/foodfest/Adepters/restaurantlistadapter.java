package com.example.foodfest.Adepters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodfest.R;
import com.example.foodfest.models.Restaurantmodel;

import java.util.List;

public class restaurantlistadapter extends RecyclerView.Adapter<restaurantlistadapter.Myviewholder> {

    private List<Restaurantmodel> restaurantmodelList;

    private  RestaurantListclickListener restaurantListclickListener;
    public restaurantlistadapter(List<Restaurantmodel> restaurantmodelList ,RestaurantListclickListener restaurantListclickListener){
        this.restaurantmodelList = restaurantmodelList;
    }
    public  void updateData(List<Restaurantmodel> restaurantmodelList){
        this.restaurantmodelList = restaurantmodelList;
        this.restaurantListclickListener= restaurantListclickListener;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public restaurantlistadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row,parent,false );
        return new Myviewholder(view);
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.restaurantname.setText(restaurantmodelList.get(position).getName());
        holder.addrase.setText( "Address:"+restaurantmodelList.get(position).getAddrass());
        holder.restouranthours.setText( "today's hours: "+ restaurantmodelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantListclickListener.onImageClick(restaurantmodelList.get(position));
            }
        });
        Glide.with(holder.thamimage)
                .load(restaurantmodelList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return restaurantmodelList.size();
    }
    static class Myviewholder extends RecyclerView.ViewHolder{
        TextView restaurantname;
        TextView addrase;
        TextView restouranthours;
        ImageView thamimage;



        public Myviewholder(View view){
        super(view);
        restaurantname = view.findViewById(R.id.restaurantname);
        addrase = view.findViewById(R.id.addrase);
        restouranthours = view.findViewById(R.id.restouranthours);
        thamimage = view.findViewById(R.id.thamimage);
        }
    }

    public interface RestaurantListclickListener{
        public void  onImageClick(Restaurantmodel restaurantmodel);
    }
}

