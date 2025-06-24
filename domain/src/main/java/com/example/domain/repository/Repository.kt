package com.example.domain.repository

import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getIdFromNetwork(): Single<String>
    fun getCardIds(): Single<List<String>>
}