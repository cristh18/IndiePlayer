package com.cristto.indieplayer.api.config;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ServiceGenerator {

    public static <S> S createService(Context context, Class<S> serviceClass) {

        Retrofit.Builder builder = RetrofitBuilderGenerator.getRetrofitBuilder(context);

        OkHttpClient client = OkHttpClientGenerator.getClient(context);
        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }
}
