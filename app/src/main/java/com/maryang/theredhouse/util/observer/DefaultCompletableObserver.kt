package com.maryang.theredhouse.util.observer

import io.reactivex.observers.DisposableCompletableObserver

abstract class DefaultCompletableObserver : DisposableCompletableObserver() {
    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}
