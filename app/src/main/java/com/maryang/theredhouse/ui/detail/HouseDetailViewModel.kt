package com.maryang.theredhouse.ui.detail

import androidx.lifecycle.MutableLiveData
import com.maryang.theredhouse.data.repository.HouseRepository
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.ui.base.BaseViewModel
import com.maryang.theredhouse.util.observer.DefaultSingleObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HouseDetailViewModel @Inject constructor(
    private val houseRepository: HouseRepository
) : BaseViewModel() {

    val houseLiveData = MutableLiveData<HouseEntity>()

    fun onCreate(id: Int) {
        getHouse(id)
    }

    fun getHouse(id: Int) {
        houseRepository.getHouse(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DefaultSingleObserver<HouseEntity>() {
                override fun onSuccess(t: HouseEntity) {
                    houseLiveData.postValue(t)
                }
            })
    }
}
