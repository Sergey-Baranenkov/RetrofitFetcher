package com.example.retrofit

import io.reactivex.Single
import retrofit2.http.GET

interface JSONTestApi {

    @GET("http://date.jsontest.com/?service=date")
    fun getDate(): Single<DateModel>

    @GET("http://date.jsontest.com/?service=ip")
    fun getIp(): Single<IpModel>
}