package com.example.homeworkandroid.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetCardIdsUseCase
import com.example.domain.usecases.GetIdFromNetworkUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val getIdFromNetworkUseCase: GetIdFromNetworkUseCase,
    private val getCardIdsUseCase: GetCardIdsUseCase,
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val inputSubject = PublishSubject.create<String>()

    private val idLiveDataMutable = MutableLiveData<String>()
    val idLiveData: LiveData<String>
        get() = idLiveDataMutable
    private val timeLiveDataMutable = MutableLiveData<String>()
    val timeLiveData: LiveData<String>
        get() = timeLiveDataMutable

    init {
        getId()
        timer()
        inputSubject()
        getCardList()
    }

    fun changeEditText(newValue: String) {
        inputSubject.onNext(newValue)
    }

    @SuppressLint("CheckResult")
    private fun getId() {
        getIdFromNetworkUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ id ->
                idLiveDataMutable.value = id
            }, { error ->
                idLiveDataMutable.value = "Error: ${error.message}"
            })
    }

    private fun timer() {
        val timerDisposable = Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { time ->
                timeLiveDataMutable.value = time.toString()
            }
        compositeDisposable.add(timerDisposable)
    }

    private fun inputSubject() {
        val editTextDisposable = inputSubject
            .debounce(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                Log.d("EditTextDebounce", "Input after 3 seconds pause: $text")
            }
        compositeDisposable.add(editTextDisposable)
    }

    @SuppressLint("CheckResult")
    private fun getCardList() {
        getCardIdsUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ idList ->
                Log.d("EditTextDebounce", "$idList")
            }, { error ->
                idLiveDataMutable.value = "Error: ${error.message}"
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}