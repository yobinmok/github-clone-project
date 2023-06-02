package com.example.data.repository

import android.util.Log
import com.example.data.api.RetrofitService
import com.example.data.userListMapperToDomain
import com.example.data.model.SearchUserListEntity
import com.example.data.model.UserEntity
import com.example.data.userMapperToDomain
import com.example.domain.NetworkResult
import com.example.domain.model.SearchUser
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService):
    UserRepository {

    private val initResult = NetworkResult.LOADING
    private var _userListResult = MutableStateFlow(initResult)
    override val userListResult: StateFlow<NetworkResult> get() = _userListResult

    private var _userResult = MutableStateFlow(initResult)
    override val userResult: StateFlow<NetworkResult> get() = _userResult

    override fun requestUser(username: String): Flow<User> = callbackFlow{
        _userResult.value = NetworkResult.LOADING
        retrofitService.requestUser(username).enqueue(object: Callback<UserEntity>{
            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                if(response.isSuccessful){
                    Log.d("User Success", response.body().toString())
                    val item = userMapperToDomain(response.body()!!)
                    _userResult.value = NetworkResult.SUCCESS
                    trySend(item)
                }else {
                    Log.e("User Not successful", response.toString())
                    _userResult.value = NetworkResult.NOT_SUCCESS
                }
            }

            override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                Log.d("User Failure", t.toString())
                _userResult.value = NetworkResult.FAIL
            }
        })
        awaitClose{
            Log.d("awaitClose", "이게 언제 호출되려나..1")
        }
    }

    override fun requestUserList(username: String): Flow<List<SearchUser>> = callbackFlow {
        _userListResult.value = NetworkResult.LOADING
        retrofitService.requestUserList(username).enqueue(object: Callback<SearchUserListEntity>{
            override fun onResponse(
                call: Call<SearchUserListEntity>, response: Response<SearchUserListEntity>
            ) {
                if(response.isSuccessful){
                    // 일치하는 사용자가 없는 경우
                    // SearchUserListEntity(total_count=0, items=[])
                    val totalCount = response.body()!!.total_count
                    var items = response.body()!!.items.map {
                        userListMapperToDomain(it)
                    }
                    if(totalCount == 0)
                        items = listOf()
                    else if(totalCount > 1){
                        items = items.subList(1,items.size)
                    }
                    _userListResult.value = NetworkResult.SUCCESS
                    Log.d("UserList Success", items.toString())
                    trySend(items)
                }else{
                    Log.e("UserList Not successful", response.toString())
                    _userListResult.value = NetworkResult.NOT_SUCCESS
                }
            }

            override fun onFailure(call: Call<SearchUserListEntity>, t: Throwable) {
                Log.d("UserList Failure", t.toString())
                _userListResult.value = NetworkResult.FAIL
            }
        })
        awaitClose{
            Log.d("awaitClose", "이게 언제 호출되려나..2")
        }
    }

}