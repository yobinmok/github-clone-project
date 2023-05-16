package com.example.data.repository

import android.util.Log
import com.example.data.api.RetrofitService
import com.example.data.mapperToDomain
import com.example.data.model.SearchUserListEntity
import com.example.data.remote.RemoteData
import com.example.domain.model.SearchUser
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService, private val remoteData: RemoteData):
    UserRepository {

    // ItemEntity를 Item으로 바꿔주는 Mapper 아아아 대박
    override fun requestUser2(login: String): Flow<SearchUser> = flow{
        val item = mapperToDomain(retrofitService.getItem(login))
        emit(item)
    }

    override fun requestUserList(username: String): Flow<List<SearchUser>> = callbackFlow {
        retrofitService.requestUserList(username).enqueue(object: Callback<SearchUserListEntity>{
            override fun onResponse(
                call: Call<SearchUserListEntity>, response: Response<SearchUserListEntity>
            ) {
                if(response.isSuccessful){
                    Log.d("requestUserList Success", response.body().toString())
                    // 일치하는 사용자가 없는 경우
                    // SearchUserListEntity(total_count=0, items=[])
                    val items = response.body()!!.items.map {
                        mapperToDomain(it)
                    }
                    trySend(items)
                }else
                    Log.e("requestUserList Not successful", response.toString())
            }

            override fun onFailure(call: Call<SearchUserListEntity>, t: Throwable) {
                Log.d("requestUserList Failure", t.toString())
            }
        })
        awaitClose{
            Log.d("awaitClose", "이게 언제 호출되려나..")
        }
    }
    override fun requestUser(username: String): Flow<User> {
        TODO("Not yet implemented")
    }
}