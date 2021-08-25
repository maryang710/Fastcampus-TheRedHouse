package com.maryang.theredhouse.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maryang.theredhouse.domain.entity.HouseEntity
import io.reactivex.Single

@Dao
interface HouseDao {
    @Query("SELECT * FROM houseentity WHERE id = :id")
    fun getHouse(id: Int): Single<HouseEntity>

    @Query("SELECT * FROM houseentity")
    fun getAllHouseList(): Single<List<HouseEntity>>

    @Insert(
        entity = HouseEntity::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insert(house: HouseEntity.Upload)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(houseList: List<HouseEntity>)
}
