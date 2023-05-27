package com.example.data.model

import com.squareup.moshi.Json

data class SearchUserListEntity (
    @Json(name = "total_count")
    val total_count: Int,
    @Json(name = "items")
    val items: List<SearchUserEntity>
)

data class SearchUserEntity (
    @Json(name = "id")
    val id: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "login")
    val login: String
)