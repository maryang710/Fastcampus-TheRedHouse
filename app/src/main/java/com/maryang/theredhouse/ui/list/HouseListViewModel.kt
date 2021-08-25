package com.maryang.theredhouse.ui.list

import androidx.lifecycle.MutableLiveData
import com.maryang.theredhouse.data.repository.HouseRepository
import com.maryang.theredhouse.data.repository.UserRepository
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.event.AnalyticsManager
import com.maryang.theredhouse.ui.base.BaseViewModel
import com.maryang.theredhouse.util.observer.DefaultCompletableObserver
import com.maryang.theredhouse.util.observer.DefaultSingleObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HouseListViewModel @Inject constructor(
    private val houseRepository: HouseRepository,
    private val userRepository: UserRepository,
    private val analyticsManager: AnalyticsManager
) : BaseViewModel() {

    val houseListLiveData = MutableLiveData<List<HouseEntity>>()
    val logoutLiveData = MutableLiveData<Boolean>()

    fun onCreate() {
        getHouseList()
    }

    fun getHouseList() {
        houseRepository.getAllHouseList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DefaultSingleObserver<List<HouseEntity>>() {
                override fun onSuccess(t: List<HouseEntity>) {
                    houseListLiveData.postValue(t)
                }
            })
    }

    fun logout() {
        userRepository.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DefaultCompletableObserver() {
                override fun onComplete() {
                    analyticsManager.setUserId(null)
                    logoutLiveData.postValue(true)
                }
            })
    }
}
