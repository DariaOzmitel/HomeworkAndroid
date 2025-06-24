package com.example.data.network

import com.example.data.network.models.CardIdsDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

internal interface ApiServiceCard1 {
    @GET("c0c469feb00a91394b06bd39d37a228b527c100b/cardId1.json")
    fun getIds(): Single<CardIdsDto>
}