package com.maryang.theredhouse.util

import android.util.Patterns

object PatternUtil {
    fun isEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
