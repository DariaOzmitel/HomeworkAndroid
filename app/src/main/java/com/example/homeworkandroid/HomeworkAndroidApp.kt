package com.example.homeworkandroid

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.homeworkandroid.android.task2.scheduleChargingNotification
import com.example.homeworkandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class HomeworkAndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@HomeworkAndroidApp)
            modules(listOf(dataModule, domainModule, appModule))
        }
        scheduleChargingNotification(this)
    }
}