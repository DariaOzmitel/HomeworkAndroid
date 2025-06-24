package com.example.homeworkandroid.rxJava

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    Observable.timer(10, TimeUnit.MILLISECONDS, Schedulers.newThread())
        .subscribeOn(Schedulers.io())
        .map {
            println("mapThread = ${Thread.currentThread().name}")
            it
        }
        .doOnSubscribe {
            println("onSubscribeThread = ${Thread.currentThread().name}")
        }
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.single())
        .flatMap {
            println("flatMapThread = ${Thread.currentThread().name}")
            Observable.just(it)
                .subscribeOn(Schedulers.io())
        }
        .subscribe {
            println("subscribeThread = ${Thread.currentThread().name}")
        }

    Thread.sleep(1000)
}

/*
1) onSubscribeThread = RxComputationThreadPool-1
Из-за того, что дальше .subscribeOn(Schedulers.computation())
2) mapThread = RxNewThreadScheduler-1
Из-за того, что у таймера указан Schedulers.newThread()
3) flatMapThread = RxSingleScheduler-1
Из-за того, что ранее использован .observeOn(Schedulers.single())
4) subscribeThread = RxCachedThreadScheduler-1
Из-за того, что flatMap возвращает Observable выполняемый на Schedulers.io()
*/