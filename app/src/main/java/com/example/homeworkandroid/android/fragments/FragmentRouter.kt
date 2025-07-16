package com.example.homeworkandroid.android.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class FragmentRouter(private val activity: FragmentActivity, private val containerId: Int) {

    fun navigateTo(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = activity.supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)

        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    fun goBack() {
        activity.supportFragmentManager.popBackStack()
    }
}