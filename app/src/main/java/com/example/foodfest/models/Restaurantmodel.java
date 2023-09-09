package com.example.foodfest.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;

import androidx.annotation.NonNull;

import java.util.List;

public class Restaurantmodel implements Parcelable {
    private String name ;
    private String addrass ;
    private String image ;
    private float delivery_charge ;
    private Hours hours;
    private List<Menu> menus;

    protected Restaurantmodel(Parcel in) {
        name = in.readString();
        addrass = in.readString();
        image = in.readString();
        delivery_charge = in.readFloat();
    }

    public static final Creator<Restaurantmodel> CREATOR = new Creator<Restaurantmodel>() {
        @Override
        public Restaurantmodel createFromParcel(Parcel in) {
            return new Restaurantmodel(in);
        }

        @Override
        public Restaurantmodel[] newArray(int size) {
            return new Restaurantmodel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrass() {
        return addrass;
    }

    public void setAddrass(String addrass) {
        this.addrass = addrass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public String setDelivery_charge() {
        this.delivery_charge = delivery_charge;
        return null;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(addrass);
        dest.writeString(image);
        dest.writeFloat(delivery_charge);
    }
}
