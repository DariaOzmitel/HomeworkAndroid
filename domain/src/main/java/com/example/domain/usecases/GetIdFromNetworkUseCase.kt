package com.example.domain.usecases

import com.example.domain.repository.Repository
import io.reactivex.rxjava3.core.Single

class GetIdFromNetworkUseCase(
    private val repository: Repository
) {
    fun invoke(): Single<String> {
        return repository.getIdFromNetwork()
    }
}