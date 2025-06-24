package com.example.homework.rxJava

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.PublishSubject

@SuppressLint("CheckResult")
fun main() {
    val subject = PublishSubject.create<String>()
    subject.onNext("1")
    subject.onNext("2")
    subject.onNext("3")
    subject.subscribe { println(it) }
}
/*
Выведено ничего не будет, потому что PublishSubject отдаёт данные только новым подписчикам
Чтобы все вывелось, можно:
1) Подписаться на события раньше:
    val subject = PublishSubject.create<String>()
    subject.subscribe { println(it) }
    subject.onNext("1")
    subject.onNext("2")
    subject.onNext("3")
2) Использовать ReplaySubject, который сохранит всю историю значений и отдаст подписчику при подписке:
    val subject = ReplaySubject.create<String>()
    subject.onNext("1")
    subject.onNext("2")
    subject.onNext("3")
    subject.subscribe { println(it) }
 */