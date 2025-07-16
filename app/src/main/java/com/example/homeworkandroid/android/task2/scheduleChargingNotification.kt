package com.example.homeworkandroid.android.task2

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

fun scheduleChargingNotification(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .build()

    val request = OneTimeWorkRequestBuilder<ChargingNotificationWorker>()
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueueUniqueWork(
        "charging_notification_work",
        ExistingWorkPolicy.KEEP,
        request
    )
}
