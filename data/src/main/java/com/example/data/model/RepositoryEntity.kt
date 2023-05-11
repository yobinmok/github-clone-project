package com.example.data.model

import com.squareup.moshi.Json
import org.json.JSONObject

data class RepositoryEntity (
//    @Json(name = "full_name")
//    val full_name: String,
//    @Json(name = "description")
//    val description: String,
//    @Json(name = "html_url")
//    val html_url: String
    @Json(name =  "total_count")
    val total_count: String
//    @Json(name =  "items")
//    val items: List<JSONObject>
)