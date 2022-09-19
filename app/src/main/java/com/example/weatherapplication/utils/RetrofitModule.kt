package com.example.weatherapplication.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitModule {

    private val TIMEOUT = 60.toLong()

    companion object {
        fun getInstance(): RetrofitModule {
            return RetrofitModule()
        }
    }

    fun providesRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor {
            var request = it.request()
            val headers =
                request.headers.newBuilder().add("Content-Type", "application/json").build()
            request = request.newBuilder().headers(headers).build()
            it.proceed(request)
        }
        okHttpBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(okHttpBuilder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addNetworkInterceptor(interceptor)
        return client.build()
    }

    fun providesGson(): Gson {
        return Gson()
    }

    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}