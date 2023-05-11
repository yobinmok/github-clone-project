package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class UserEntity (
    @Json(name = "id")
    @PrimaryKey
    val id: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "login")
    val login: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "followers")
    val followers: String,
    @Json(name = "following")
    val following: String
)