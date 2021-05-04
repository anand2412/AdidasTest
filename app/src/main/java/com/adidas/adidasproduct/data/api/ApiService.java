package com.adidas.adidasproduct.data.api;

import com.adidas.adidasproduct.data.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("product")
    Call<List<ProductResponse>> getAllProducts();
}
