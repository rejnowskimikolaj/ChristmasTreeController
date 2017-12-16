package com.example.root.christmastree.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 12/15/17.
 */

public class ApiClientFactory {
    OkHttpClient.Builder builder =  new OkHttpClient.Builder();
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public TreeApiClient create(String url,String port) {

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url+":"+port)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
                .build();

        return retrofit.create(TreeApiClient.class);
    }
}
