package com.maryang.theredhouse.data.repository

import com.maryang.theredhouse.data.db.UserDao
import com.maryang.theredhouse.data.preferences.UserPreferences
import com.maryang.theredhouse.domain.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userPreferences: UserPreferences
) {
    fun signup(email: String) =
        Single.fromCallable {
            val userId = userDao.insert(UserEntity.Email(email)).toInt()
            userPreferences.putIsSignup(true)
            userPreferences.putUserId(userId)
            userId
        }

    fun logout() =
        Completable.fromCallable {
            userPreferences.putIsSignup(false)
            userPreferences.putUserId(null)
        }
}
