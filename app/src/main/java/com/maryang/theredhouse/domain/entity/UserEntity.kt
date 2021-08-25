package com.maryang.theredhouse.domain.entity

import androidx.room.PrimaryKey

@androidx.room.Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    val email: String
) : Entity, Identifier {
    data class Email(
        val email: String
    )
}
