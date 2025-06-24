package com.example.domain.usecases

import com.example.domain.repository.Repository
import io.reactivex.rxjava3.core.Single

class GetCardIdsUseCase(
    private val repository: Repository
) {
    fun invoke(): Single<List<String>> {
        return repository.getCardIds()
    }
}