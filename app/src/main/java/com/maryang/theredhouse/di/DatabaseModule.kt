package com.maryang.theredhouse.di

import android.content.Context
import androidx.room.Room
import com.maryang.theredhouse.data.db.HouseDao
import com.maryang.theredhouse.data.db.TheRedDb
import com.maryang.theredhouse.data.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideTheRedDb(@ApplicationContext context: Context): TheRedDb {
        return Room.databaseBuilder(context, TheRedDb::class.java, "thered.db").build()
    }

    @Provides
    @Singleton
    fun provideHouseDao(theRedDb: TheRedDb): HouseDao {
        return theRedDb.houseDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(theRedDb: TheRedDb): UserDao {
        return theRedDb.userDao()
    }
}
