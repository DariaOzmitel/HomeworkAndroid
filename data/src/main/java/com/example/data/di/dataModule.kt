package com.example.data.di

import com.example.data.network.ApiFactory
import com.example.data.network.ApiFactoryCard1
import com.example.data.network.ApiFactoryCard2
import com.example.data.network.ApiService
import com.example.data.network.ApiServiceCard1
import com.example.data.network.ApiServiceCard2
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::RepositoryImpl) bind Repository::class
    single<ApiService> { ApiFactory.apiService }
    single<ApiServiceCard1> { ApiFactoryCard1.apiService }
    single<ApiServiceCard2> { ApiFactoryCard2.apiService }
}
