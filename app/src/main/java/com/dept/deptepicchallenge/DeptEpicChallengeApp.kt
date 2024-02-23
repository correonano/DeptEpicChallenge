package com.dept.deptepicchallenge

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeptEpicChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("dept", "dept")
    }
}