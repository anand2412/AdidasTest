package com.adidas.adidasproduct.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.adidas.adidasproduct.data.api.ApiHelper;
import com.adidas.adidasproduct.data.model.ProductResponse;
import com.adidas.adidasproduct.data.repository.DataRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private ApiHelper mApiHelper;
    public ViewModelFactory(ApiHelper apiHelper){
        mApiHelper = apiHelper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductResponseViewModel.class)) {
            return (T) new ProductResponseViewModel(new DataRepository(mApiHelper));
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
