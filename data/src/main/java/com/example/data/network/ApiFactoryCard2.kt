package com.example.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal object ApiFactoryCard2 {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/DariaOzmitel/0ac72b643e334471183db170a61a49aa/raw/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val apiService: ApiServiceCard2 = retrofit.create(ApiServiceCard2::class.java)
}