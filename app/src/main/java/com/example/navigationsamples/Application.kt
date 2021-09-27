package com.example.navigationsamples

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.example.navigationsamples.vas.VasActivity
import com.example.navigationsamples.vas.di.VasComponent
import com.joom.lightsaber.Lightsaber

class Application: Application() {

    var lightsaber: Lightsaber = Lightsaber.Builder().build()

    override fun onCreate() {
        super.onCreate()
        Log.e("DI", "start DI Application")
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
                super.onActivityPreCreated(activity, savedInstanceState)
                //gross hack don't do this =)
                if (activity is VasActivity) {
                    Log.e("DI", "Activity is VasActivity")
                    //todo make wrapper
                    val injector = lightsaber.createInjector(VasComponent())
                    injector.injectMembers(activity)
                }
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }

}