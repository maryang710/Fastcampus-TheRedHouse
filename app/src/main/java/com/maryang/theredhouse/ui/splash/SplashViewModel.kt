package com.maryang.theredhouse.ui.splash

import androidx.lifecycle.MutableLiveData
import com.maryang.theredhouse.data.preferences.UserPreferences
import com.maryang.theredhouse.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : BaseViewModel() {

    val shouldSignupLiveData = MutableLiveData<Boolean>()

    fun onCreate() {
        shouldSignupLiveData.postValue(!userPreferences.isSignup())
    }
}
