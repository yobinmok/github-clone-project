package com.example.data.api

import com.example.data.model.SearchUserEntity
import com.example.data.model.RepositoryEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("/users")
    suspend fun getAllItem(): List<SearchUserEntity>
    @GET("/users/{username}")
    suspend fun getItem(@Path("username") login: String): SearchUserEntity

    @GET("/search/repositories")
    fun getRepository(@Query("q") repository: String): Call<RepositoryEntity>
}
