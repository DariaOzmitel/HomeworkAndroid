package com.example.data.network

import com.example.data.network.models.IdDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

internal interface ApiService {
    @GET("1e71281138c8a3783822a26b7c928df09b540e92/mockId.json")
    fun getId(): Single<IdDto>
}