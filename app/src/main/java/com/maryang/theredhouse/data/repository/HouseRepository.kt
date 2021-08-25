package com.maryang.theredhouse.data.repository

import com.maryang.theredhouse.data.db.HouseDao
import com.maryang.theredhouse.domain.entity.HouseEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HouseRepository @Inject constructor(
    private val houseDao: HouseDao
) {

    fun getHouse(id: Int): Single<HouseEntity> =
        houseDao.getHouse(id)

    fun getAllHouseList(): Single<List<HouseEntity>> =
        houseDao.getAllHouseList()

    fun upload(house: HouseEntity.Upload) =
        Completable.fromCallable {
            houseDao.insert(house)
        }

    fun insertAll(houseList: List<HouseEntity>) {
        Completable.fromCallable {
            houseDao.insertAll(houseList)
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}
