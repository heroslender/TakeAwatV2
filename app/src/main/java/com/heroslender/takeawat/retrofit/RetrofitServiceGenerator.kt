package com.heroslender.takeawat.retrofit

import android.content.Context
import android.util.Log
import com.heroslender.takeawat.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServiceGenerator {

    fun getClient(context: Context? = null): RetrofitClient {
        val client = generate(RetrofitClient::class.java, BuildConfig.BASE_URL, context)
        Log.d("RetrofitService", "Client generated...")

        return client
    }

    fun <T> generate(serviceClass: Class<T>, baseUrl: String, context: Context?): T {
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder.connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        httpBuilder.protocols(listOf(Protocol.HTTP_1_1))
        httpBuilder.readTimeout(30, TimeUnit.SECONDS)
        httpBuilder.writeTimeout(30, TimeUnit.SECONDS)
        httpBuilder.connectTimeout(30, TimeUnit.SECONDS)
        if (context != null) {
            httpBuilder.addInterceptor(NetworkConnectionInterceptor(context))
        }

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpBuilder.addInterceptor(interceptor)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(DateConverterFactory.create())
            .build()
        return retrofit.create(serviceClass)
    }
}