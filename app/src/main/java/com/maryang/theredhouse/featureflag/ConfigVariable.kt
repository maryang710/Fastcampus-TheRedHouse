package com.maryang.theredhouse.featureflag

sealed class ConfigVariable<T> {
    abstract val key: String
    abstract val defaultValue: T

    fun toPair() = key to defaultValue
}
