package com.maryang.theredhouse.domain.entity

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@androidx.room.Entity
data class HouseEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    val name: String,
    @DrawableRes val imageResId: Int?,
    val price: Long,
    val address: String,
    val contact: String,
    val type: HouseType
) : Entity, Identifier {
    @Parcelize
    data class Upload(
        val name: String,
        val price: Long,
        val address: String,
        val contact: String,
        val type: HouseType
    ) : Parcelable
}

enum class HouseType(val displayName: String) {
    APART("아파트"), VILLA("빌라")
}
