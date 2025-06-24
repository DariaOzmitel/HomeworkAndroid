package com.example.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal object ApiFactoryCard1 {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/DariaOzmitel/98f1ff214b6d920c5385b42c1f8046b7/raw/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val apiService: ApiServiceCard1 = retrofit.create(ApiServiceCard1::class.java)
}