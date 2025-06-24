package com.example.data.network

import com.example.data.network.models.CardIdsDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

internal interface ApiServiceCard2 {
    @GET("2437424913e03926b33a54bdc6050fa218de4ddb/cardId2.json")
    fun getIds(): Single<CardIdsDto>
}