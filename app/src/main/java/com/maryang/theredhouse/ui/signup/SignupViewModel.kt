package com.maryang.theredhouse.ui.signup

import androidx.lifecycle.MutableLiveData
import com.maryang.theredhouse.data.repository.UserRepository
import com.maryang.theredhouse.event.AnalyticsManager
import com.maryang.theredhouse.event.EventDefinitions
import com.maryang.theredhouse.ui.base.BaseViewModel
import com.maryang.theredhouse.util.observer.DefaultSingleObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val analyticsManager: AnalyticsManager
) : BaseViewModel() {

    val signupLiveData = MutableLiveData<Boolean>()
    val emailState = BehaviorSubject.createDefault(false)
    val passwordState = BehaviorSubject.createDefault(false)
    val checkPolicyState = BehaviorSubject.createDefault(false)
    val buttonState = BehaviorSubject.createDefault(false)

    init {
        compositeDisposable += Observable.combineLatest(
            emailState,
            passwordState,
            checkPolicyState,
            { emailState, passwordState, checkPolicyState ->
                emailState && passwordState && checkPolicyState
            }
        ).subscribe(buttonState::onNext)
    }

    fun signup(email: String, password: String, checkPolicy: Boolean) {
        userRepository.signup(email)
            .doOnSuccess { userId ->
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DefaultSingleObserver<Int>() {
                override fun onSuccess(t: Int) {
                    signupLiveData.postValue(true)
                }
            })
    }
}
