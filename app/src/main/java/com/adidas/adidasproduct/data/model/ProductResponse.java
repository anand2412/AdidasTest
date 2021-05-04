package com.adidas.adidasproduct.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ProductResponse implements Parcelable {

    private String currency;
    private int price;
    private String id;
    private String name;
    private String description;
    private String imgUrl;
    private List<Reviews> reviews;

    public ProductResponse() {

    }
    protected ProductResponse(Parcel in) {
        currency = in.readString();
        price = in.readInt();
        id = in.readString();
        name = in.readString();
        description = in.readString();
        imgUrl = in.readString();
        reviews = in.createTypedArrayList(Reviews.CREATOR);
    }

    public static final Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {
        @Override
        public ProductResponse createFromParcel(Parcel in) {
            return new ProductResponse(in);
        }

        @Override
        public ProductResponse[] newArray(int size) {
            return new ProductResponse[size];
        }
    };

    public String getCurrency() {
        return currency;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currency);
        dest.writeInt(price);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imgUrl);
        dest.writeTypedList(reviews);
    }
}
