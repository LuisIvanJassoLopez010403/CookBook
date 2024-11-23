package com.example.cookbook.network

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientInstance {

    private var retrofit: Retrofit? = null

    fun getRetrofitInstance(token: String?): Retrofit {
        return retrofit ?: synchronized(this) {
            retrofit ?: buildRetrofitApi(token).also { retrofit = it }
        }
    }

    val apiService: ApiService = buildRetrofitApi().create(ApiService::class.java)


    private fun buildRetrofitApi(token: String? = null): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val tokenInterceptor = { chain: okhttp3.Interceptor.Chain ->
            val requestBuilder = chain.request().newBuilder()
            token?.let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
            chain.proceed(requestBuilder.build())
        }

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://cookbookserver.onrender.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}