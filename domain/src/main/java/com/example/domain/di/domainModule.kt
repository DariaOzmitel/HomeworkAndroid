package com.example.domain.di

import com.example.domain.usecases.GetCardIdsUseCase
import com.example.domain.usecases.GetIdFromNetworkUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetIdFromNetworkUseCase)
    singleOf(::GetCardIdsUseCase)
}