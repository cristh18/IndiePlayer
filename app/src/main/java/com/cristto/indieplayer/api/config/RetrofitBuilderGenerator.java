package com.cristto.indieplayer.api.config;

import android.content.Context;

import com.cristto.indieplayer.app.IndiePlayerApplication;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilderGenerator {

//    public static final String BASE_URL = IndiePlayerApplication.getBaseUrl();
    public static final String BASE_URL = "https://api.soundcloud.com/";
    private static Retrofit.Builder retrofitBuilder;

    private RetrofitBuilderGenerator() {
    }

    public synchronized static Retrofit.Builder getRetrofitBuilder(Context context) {
        if (retrofitBuilder == null) {
            createRetrofitBuilder(context);
        }
        return retrofitBuilder;
    }

    private static void createRetrofitBuilder(Context context) {
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }
}
