package com.adidas.adidasproduct.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adidas.adidasproduct.data.model.ProductResponse;
import com.adidas.adidasproduct.data.repository.DataRepository;
import com.adidas.adidasproduct.util.APIError;
import com.adidas.adidasproduct.util.ErrorUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductResponseViewModel extends ViewModel {

    private DataRepository mDataRepository;
    public MutableLiveData<List<ProductResponse>> productResponse = new MutableLiveData<>();
    public MutableLiveData<APIError> error = new MutableLiveData<>();

    public ProductResponseViewModel(DataRepository dataRepository) {
        mDataRepository = dataRepository;
    }

    public void getAllProducts() {
        mDataRepository.getAllProducts().enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if (response.isSuccessful()) {
                    productResponse.postValue(response.body());
                } else {
                    APIError apiError = ErrorUtils.parseError(response);
                    error.postValue(apiError);
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                APIError apiError = new APIError();
                apiError.setMessage("Please check your internet connection!!!");
                apiError.setStatusCode(000);
                error.postValue(apiError);
            }
        });
    }
}
