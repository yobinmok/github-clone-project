package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

//@Entity
data class RepositoryEntity (
//    @PrimaryKey
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "owner")
    val owner: RepoOwner,
    @Json(name = "description")
    val description: String?,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "topics")
    val topics: List<String>?,
    @Json(name="stargazers_count")
    val star: String,
    @Json(name="language")
    val language: String?
)

data class RepoOwner(
    @Json(name = "login")
    val login: String
)