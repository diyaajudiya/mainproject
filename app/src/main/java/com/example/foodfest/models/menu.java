package com.example.foodfest.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class menu implements Parcelable{

    private String name;
    private float price;
    private int totalInCard;
    private String url;

    public int getTotalInCard() {
        return totalInCard;
    }

    public void setTotalInCard(int totalInCard) {
        this.totalInCard = totalInCard;
    }

    protected menu(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        url = in.readString();
        totalInCard = in.readInt();
    }



    public static final Creator<menu> CREATOR = new Creator<menu>() {
        @Override
        public menu createFromParcel(Parcel in) {
            return new menu(in);
        }

        @Override
        public menu[] newArray(int size) {
            return new menu[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeString(url);
        dest.writeInt(totalInCard);
    }
}
