package com.example.data.model

import com.squareup.moshi.Json

data class SearchRepoEntity (
    @Json(name =  "total_count")
    val total_count: Int,
    @Json(name = "items")
    val items: List<RepositoryEntity>
)