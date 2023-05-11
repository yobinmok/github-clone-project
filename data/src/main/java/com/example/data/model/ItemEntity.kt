package com.example.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

// 통신에 사용되는 모델 (Room을 사용할 경우, Entity 선언해서 Entity로도 사용)
@Parcelize
data class ItemEntity (
    @Json(name = "id")
    val id: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "login")
    val login: String
) : Parcelable