package com.luizeduardobrandao.retrofit.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    // Padrão Singleton
    companion object {

        private lateinit var instance: Retrofit

        fun getRetrofitClient(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            synchronized(this){
                if (!::instance.isInitialized){
                    instance = Retrofit.Builder()
                        .client(httpClient.build())
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return instance
        }

        fun <S> createService(serviceClass: Class<S>): S{
            return getRetrofitClient().create(serviceClass)
        }
    }
}