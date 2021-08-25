package com.maryang.theredhouse.ui.upload

import androidx.lifecycle.MutableLiveData
import com.maryang.theredhouse.data.repository.HouseRepository
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.event.AnalyticsManager
import com.maryang.theredhouse.event.EventDefinitions
import com.maryang.theredhouse.ui.base.BaseViewModel
import com.maryang.theredhouse.util.observer.DefaultCompletableObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UploadContactViewModel @Inject constructor(
    private val houseRepository: HouseRepository,
    private val analyticsManager: AnalyticsManager
) : BaseViewModel() {

    val uploadLiveData = MutableLiveData<Boolean>()

    fun upload(house: HouseEntity.Upload) {
        houseRepository.upload(house)
            .doOnComplete {
                analyticsManager.logEvent(EventDefinitions.completeUploadHouse())
                analyticsManager.setUserProperty(AnalyticsManager.PROPERTY_HOUSE_HOST, true.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DefaultCompletableObserver() {
                override fun onComplete() {
                    uploadLiveData.postValue(true)
                }
            })
    }
}
