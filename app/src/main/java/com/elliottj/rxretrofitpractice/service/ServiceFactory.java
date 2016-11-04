package com.elliottj.rxretrofitpractice.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by elliottj on 11/3/16.
 */

public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endpoint) {
        final Retrofit restAdapter = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endpoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}
