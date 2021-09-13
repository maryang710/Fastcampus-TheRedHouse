package com.maryang.theredhouse.featureflag

import android.util.Log
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.maryang.theredhouse.BuildConfig

object RemoteConfigManager {
    fun initialize() {
        Firebase.remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds =
                    if (BuildConfig.DEBUG) 1
                    else 3600
            }
        )
        Firebase.remoteConfig.setDefaultsAsync(
            mapOf<String, Any>(
            )
        )
        Firebase.remoteConfig.fetchAndActivate()
    }

    fun getBoolean(variable: ConfigVariable<Boolean>): Boolean =
        Firebase.remoteConfig.getBoolean(variable.key)

    fun getString(variable: ConfigVariable<String>): String =
        Firebase.remoteConfig.getString(variable.key)

    fun getLong(variable: ConfigVariable<Long>): Long =
        Firebase.remoteConfig.getLong(variable.key)

    fun getDouble(variable: ConfigVariable<Double>): Double =
        Firebase.remoteConfig.getDouble(variable.key)
}
