package com.example.data.api

import com.example.data.model.ItemEntity
import com.example.data.model.RepositoryEntity
import com.example.domain.Item
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("/users")
    suspend fun getAllItem(): List<ItemEntity>
    @GET("/users/{username}")
    suspend fun getItem(@Path("username") login: String): ItemEntity

    @GET("/search/repositories")
    fun getRepository(@Query("q") repository: String): Call<RepositoryEntity>

}
