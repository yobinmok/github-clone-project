package com.example.data.api

import com.example.data.model.SearchUserEntity
import com.example.data.model.RepositoryEntity
import com.example.data.model.SearchRepoEntity
import com.example.data.model.SearchUserListEntity
import com.example.data.model.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    // 확인용 -> 최종본에는 사용 X
    @GET("/users")
    suspend fun getAllItem(): List<SearchUserEntity>
    @GET("/users/{username}")
    suspend fun getItem(@Path("username") login: String): SearchUserEntity

    // 사용자 검색
    @GET("/users/{username}") // 딱 그 이름을 가진 사용자 -> 데이터 다양
    fun requestUser(@Path("username") username: String): Call<UserEntity>
    @GET("/search/users") // 검색내용이 이름에 포함되는 사용자 리스트
    fun requestUserList(@Query("q") username: String): Call<SearchUserListEntity>


    // 리포지터리 검색
    @GET("/search/repositories")
    fun requestRepoList(@Query("q") repository: String): Call<SearchRepoEntity>//Call<List<RepositoryEntity>>
    // 리포지터리 세부정보(?)
    @GET("/repo/{repoName}") // 딱 그 이름을 가진 리포지터리 -> 세부정보에 다른 데이터를 출력할 때
    fun requestRepo(@Path("repoName") repo: String): Call<RepositoryEntity>
}
