package com.example.homeworkandroid.rxJava.viewModels

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.subjects.PublishSubject

class SharedViewModel : ViewModel() {
    val clickSubject: PublishSubject<Int> = PublishSubject.create()
}