package com.note.cesar.weathernow.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shekh on 05-02-2018.
 */

public class APIClient {

    public static APIInterface getClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantsHolder.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();

        APIInterface apiInterface = retrofit.create(APIInterface.class);
        return apiInterface;

    }

}
