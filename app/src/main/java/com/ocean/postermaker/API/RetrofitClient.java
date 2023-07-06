package com.ocean.postermaker.API;


import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.params.ConnRoutePNames;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit2 = null;


    public static Retrofit getApiClient2() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder;
            requestBuilder = original.newBuilder();

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
//        String proxyHost = android.net.Proxy.getDefaultHost();
//        int proxyPort = android.net.Proxy.getDefaultPort();
//        if (proxyHost != null && proxyPort > 0) {
//            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
//            client.g.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
//        }
        client.addInterceptor(httpLoggingInterceptor);
        client.connectTimeout(180, TimeUnit.SECONDS);
        client.readTimeout(180, TimeUnit.SECONDS);
        client.writeTimeout(180, TimeUnit.SECONDS);

        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl("https://dev.oceanmtechdmt.in/api/v4/")
//                    .baseUrl("https://dev.oceanmtechdmt.in/api/v2/")

                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit2;

    }

    public static Retrofit getApiClient3() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder;
            requestBuilder = original.newBuilder();

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        client.addInterceptor(httpLoggingInterceptor);
        client.connectTimeout(180, TimeUnit.SECONDS);
        client.readTimeout(180, TimeUnit.SECONDS);
        client.writeTimeout(180, TimeUnit.SECONDS);

        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl("http://134.209.157.161/static/sagar/")
                    .client(client.build())

                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit2;

    }

}
