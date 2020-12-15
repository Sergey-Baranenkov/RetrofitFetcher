package com.example.retrofit

import io.reactivex.Single
import retrofit2.http.GET

interface JSONTestApi {

    @GET("?service=date")
    fun getDate(): Single<DateModel>

    @GET("?service=ip")
    fun getIp(): Single<IpModel>
}