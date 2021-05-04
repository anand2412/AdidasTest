package com.adidas.adidasproduct.ui.viewmodel;

import androidx.lifecycle.Observer;

import com.adidas.adidasproduct.BaseTest;
import com.adidas.adidasproduct.data.api.ApiHelper;
import com.adidas.adidasproduct.data.model.ProductResponse;
import com.adidas.adidasproduct.data.repository.DataRepository;

import org.junit.Test;
import org.mockito.Mockito;


import retrofit2.Response;

public class ProductResponseViewModelTest extends BaseTest {

    @Test
    void test1() {
        ProductResponse productResponse = new ProductResponse();
        Response mockResponseSuccess = Response.success(productResponse);
        ApiHelper mockApiHelper = Mockito.mock(ApiHelper.class);
        //when(mockApiHelper.getAllProducts()).thenReturn(mockResponseSuccess);
        DataRepository dataRepository = new DataRepository(mockApiHelper);
        ProductResponseViewModel productResponseModel = new ProductResponseViewModel(dataRepository);
        productResponseModel.productResponse.observeForever(productResponses -> {
        });
        productResponseModel.getAllProducts();

    }
}
