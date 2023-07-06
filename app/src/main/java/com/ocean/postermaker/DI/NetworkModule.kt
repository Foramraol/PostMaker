package com.oceanmtech.dmt.DI

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ocean.postermaker.OceanmtechPosterMakerApp.Companion.context
import com.oceanmtech.dmt.Data.DataManager
import com.oceanmtech.dmt.Network.API.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    single { createApiService(get()) }

//    single { createRetrofit(get(), "https://oceanmtechdmt.com/postmaker/api/v1/") }
    single { createRetrofit(get(), "https://oceanmtechdmt.in/postmaker/api/v1/") }
//    single { createRetrofit2(get(), "https://oceanmtechdmt.in/postmaker/api/v1/", context!!.applicationContext) }
    single { createOkHttpClient() }


}


private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().also {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.NONE
            level = HttpLoggingInterceptor.Level.BODY
        }
        it.addInterceptor(loggingInterceptor)
        it.connectTimeout(180, TimeUnit.SECONDS)
        it.readTimeout(180, TimeUnit.SECONDS)
    }.build()
}

private fun createRetrofit(okHttpClient: OkHttpClient, bashUrl: String): Retrofit {

    var client: OkHttpClient.Builder = okHttpClient.newBuilder()
    client.addInterceptor(Interceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("X-localization", "en")
            .addHeader("Accept", "application/json")
        val request: Request = requestBuilder.build()
        chain.proceed(request)

    })
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl(bashUrl)
        .client(client.build())
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
}

private fun createRetrofit2(okHttpClient: OkHttpClient, bashUrl: String, context: Context): Retrofit {

    var client: OkHttpClient.Builder = okHttpClient.newBuilder()
    client.addInterceptor(Interceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", DataManager(context).getToken())
            .addHeader("X-localization", "en")
            .addHeader("Accept", "application/json")
        val request: Request = requestBuilder.build()
        chain.proceed(request)

    })
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl(bashUrl)
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
}


private fun createRetrofit1(okHttpClient: OkHttpClient, bashUrl: String): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl(bashUrl)
        .client(okHttpClient)
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
}


fun createApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}