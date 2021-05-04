package com.adidas.adidasproduct.data.repository;

import com.adidas.adidasproduct.data.api.ApiHelper;
import com.adidas.adidasproduct.data.model.ProductResponse;

import java.util.List;

import retrofit2.Call;

public class DataRepository {

    private ApiHelper mApiHelper;
    public DataRepository(ApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }

    public Call<List<ProductResponse>> getAllProducts(){
       return mApiHelper.getAllProducts();
    }
}
