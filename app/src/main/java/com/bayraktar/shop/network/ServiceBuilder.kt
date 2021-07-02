package com.bayraktar.shop.network

import com.bayraktar.shop.BASE_URL
import com.bayraktar.shop.model.MobileResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ServiceBuilder {
    private val client = OkHttpClient
        .Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(IVitrinova::class.java)

    fun buildService(): IVitrinova {
        return retrofit
    }
}

interface IVitrinova {
    @GET("v2/discover")
    fun getDiscover(): Observable<List<MobileResponse>>
}