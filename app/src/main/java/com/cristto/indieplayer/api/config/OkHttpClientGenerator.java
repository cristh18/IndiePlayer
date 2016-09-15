package com.cristto.indieplayer.api.config;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientGenerator {

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    private static OkHttpClient client;

    private OkHttpClientGenerator() {
    }

    public synchronized static OkHttpClient getClient(Context context) {
        if (client == null) {
            addRetrofitInterceptor(context);
            client = httpClientBuilder.build();
        }
        return client;
    }

    private static void addRetrofitInterceptor(final Context context) {
        httpClientBuilder.addInterceptor(chain -> {
            Request finalRequest = chain.request();
            return chain.proceed(finalRequest);
        });
    }
}
