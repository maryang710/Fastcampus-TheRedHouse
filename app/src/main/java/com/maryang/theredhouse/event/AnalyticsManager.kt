package com.maryang.theredhouse.event

import android.content.Context
import android.os.Bundle
import com.amplitude.api.Amplitude
import com.amplitude.api.Identify
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsManager @Inject constructor(
    @ApplicationContext context: Context
) {

    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    private val amplitude = Amplitude.getInstance()

    companion object{
        const val PROPERTY_HOUSE_HOST = "house_host"
    }

    fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
        amplitude.userId = userId
    }

    fun setUserProperty(name: String, value: String?) {
        firebaseAnalytics.setUserProperty(name, value)
        amplitude.identify(Identify().set(name, value))
    }

    fun logEvent(event: EventDefinition) {
        firebaseAnalytics.logEvent(event.eventName, Bundle())
        amplitude.logEvent(event.eventName)
    }
}
