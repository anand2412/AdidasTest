package com.adidas.adidasproduct.data.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.adidas.adidasproduct.util.Constants.BASE_URL;

public class RetrofitBuilder {

    private static Retrofit retrofit;
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public ApiService mApiService = getRetrofitInstance().create(ApiService.class);

    public static Retrofit getRetrofitInstance() {
        builder.addInterceptor(new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
