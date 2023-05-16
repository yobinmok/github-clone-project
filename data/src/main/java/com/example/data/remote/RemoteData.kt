package com.example.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.data.api.RetrofitService
import com.example.data.mapperToDomain
import com.example.data.model.RepositoryEntity
import com.example.data.model.SearchUserEntity
import com.example.data.model.SearchUserListEntity
import com.example.data.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RemoteData @Inject constructor(private val retrofitService: RetrofitService){

    lateinit var repoList:List<RepositoryEntity>
    lateinit var repo:RepositoryEntity

    // username 포함된 사용자 리스트
    fun requestUserList(username: String): Flow<List<SearchUserEntity>> = flow{
        lateinit var items: List<SearchUserEntity>
        retrofitService.requestUserList(username).enqueue(object: Callback<SearchUserListEntity>{
            override fun onResponse(
                call: Call<SearchUserListEntity>, response: Response<SearchUserListEntity>
            ) {
                if(response.isSuccessful){
                    Log.d("requestUserList Success", response.body().toString())
                    items = response.body()!!.items
                }else
                    Log.e("requestUserList Not successful", response.toString())
            }

            override fun onFailure(call: Call<SearchUserListEntity>, t: Throwable) {
                Log.d("requestUserList Failure", t.toString())
            }
        })
        items.map {
            mapperToDomain(it)
        }
        emit(items)
    }

    // username과 정확히 일치하는 사용자
    fun requestUser(username: String): Flow<UserEntity> = flow{
        lateinit var user:UserEntity
        retrofitService.requestUser(username).enqueue(object: Callback<UserEntity>{
            override fun onResponse(
                call: Call<UserEntity>, response: Response<UserEntity>
            ) {
                if(response.isSuccessful){
                    Log.d("requestUser Success", response.body().toString())
                    user = response.body()!!
                }else
                    Log.e("requestUser Not successful", response.toString())
            }

            override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                Log.d("requestUser Failure", t.toString())
            }
        })
        emit(user)
    }
}