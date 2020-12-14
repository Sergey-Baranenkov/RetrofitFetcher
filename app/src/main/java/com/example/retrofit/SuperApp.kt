package com.example.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SuperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(JSONTestApi::class.java)
    }

    companion object {

        private lateinit var retrofit: JSONTestApi
        private const val BASE_URL = "http://date.jsontest.com"

        fun getClient() = retrofit

    }
}