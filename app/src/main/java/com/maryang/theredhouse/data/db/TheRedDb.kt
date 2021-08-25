package com.maryang.theredhouse.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.domain.entity.UserEntity

@Database(
    entities = arrayOf(
        HouseEntity::class,
        UserEntity::class
    ),
    version = 1
)
abstract class TheRedDb : RoomDatabase() {
    abstract fun houseDao(): HouseDao
    abstract fun userDao(): UserDao
}
