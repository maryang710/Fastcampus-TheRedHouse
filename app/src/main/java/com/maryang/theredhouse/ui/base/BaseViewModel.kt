package com.maryang.theredhouse.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
