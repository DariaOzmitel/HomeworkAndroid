package com.example.data.repository

import com.example.data.network.ApiService
import com.example.data.network.ApiServiceCard1
import com.example.data.network.ApiServiceCard2
import com.example.domain.repository.Repository
import io.reactivex.rxjava3.core.Single

internal class RepositoryImpl(
    private val apiService: ApiService,
    private val apiServiceCard1: ApiServiceCard1,
    private val apiServiceCard2: ApiServiceCard2,
) : Repository {
    override fun getIdFromNetwork(): Single<String> = apiService.getId().map { it.id }
    override fun getCardIds(): Single<List<String>> =
        Single.zip(
            serverCard1Single,
            serverCard2Single
        ) { list1, list2 ->
            list1 + list2
        }

    private val serverCard1Single = apiServiceCard1.getIds().map { it.ids }
    private val serverCard2Single = apiServiceCard2.getIds().map { it.ids }
}