package com.example.navigationsamples

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomAppTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, com.example.navigationsamples.Application::class.java.name, context)
    }
}