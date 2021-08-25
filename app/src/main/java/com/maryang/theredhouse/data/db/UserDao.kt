package com.maryang.theredhouse.data.db

import androidx.room.Dao
import androidx.room.Insert
import com.maryang.theredhouse.domain.entity.UserEntity

@Dao
interface UserDao {
    @Insert(entity = UserEntity::class)
    fun insert(user: UserEntity.Email): Long
}
