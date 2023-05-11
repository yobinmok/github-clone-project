package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class RepositoryEntity (
//    @Json(name = "full_name")
//    val full_name: String,
//    @Json(name = "description")
//    val description: String,
//    @Json(name = "html_url")
//    val html_url: String
    @Json(name =  "total_count")
    @PrimaryKey
    val total_count: String,
//    @Json(name =  "items")
//    val items: List<>
)