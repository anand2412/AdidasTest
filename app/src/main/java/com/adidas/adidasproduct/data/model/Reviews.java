package com.adidas.adidasproduct.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Reviews implements Parcelable {

    private String productId;
    private String text;
    private int rating;

    protected Reviews(Parcel in) {
        productId = in.readString();
        text = in.readString();
        rating = in.readInt();
    }

    public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel in) {
            return new Reviews(in);
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    };

    public int getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(text);
        dest.writeInt(rating);
    }
}
