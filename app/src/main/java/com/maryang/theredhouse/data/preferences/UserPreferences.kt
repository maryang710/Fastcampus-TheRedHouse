package com.maryang.theredhouse.data.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext context: Context
) : BasePreferences(context) {

    override val preferencesName: String = "user.preferences"

    private val isSignupKey = "is_signup"
    private val userIdKey = "user_id"

    fun isSignup(): Boolean {
        return getBoolean(isSignupKey, false)
    }

    fun putIsSignup(isSignup: Boolean) {
        put(isSignupKey, isSignup)
    }

    fun getUserId(): Int {
        return getInt(userIdKey, -1)
    }

    fun putUserId(userId: Int?) {
        put(userIdKey, userId)
    }
}
