package com.example.homeworkandroid.di

import com.example.homeworkandroid.viewModels.MainViewModel
import com.example.homeworkandroid.viewModels.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::SharedViewModel)
}