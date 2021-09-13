package com.maryang.theredhouse.ui.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.amplitude.api.Amplitude
import com.maryang.theredhouse.R
import com.maryang.theredhouse.data.mock.MockDataProvider
import com.maryang.theredhouse.data.repository.HouseRepository
import com.maryang.theredhouse.event.AnalyticsManager
import com.maryang.theredhouse.event.EventDefinitions
import com.maryang.theredhouse.featureflag.RemoteConfigManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application() {

    @Inject
    lateinit var houseRepository: HouseRepository

    @Inject
    lateinit var analyticsManager: AnalyticsManager

    override fun onCreate() {
        super.onCreate()
        initDatabase()
        initAmplitude()
        RemoteConfigManager.initialize()
        registerActivityLifecycleCallback()
    }

    private fun initDatabase() {
        houseRepository.insertAll(MockDataProvider.houseList())
    }

    private fun initAmplitude() {
        Amplitude.getInstance().initialize(this, getString(R.string.amplitude_key))
            .enableForegroundTracking(this)
    }

    private fun registerActivityLifecycleCallback() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            private var running = 0

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
                if (++running == 1) {
                }
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
                --running
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }
}
