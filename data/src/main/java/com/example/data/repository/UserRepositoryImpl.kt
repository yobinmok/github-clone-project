package com.example.data.repository

import android.util.Log
import com.example.data.api.RetrofitService
import com.example.data.mapperToDomain
import com.example.data.model.RepositoryEntity
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService):
    UserRepository {

    /*
        - remoteDataSource처럼 아예 api 인터페이스를 구현한 파일
        - localDataSource는 Dao를 구현한 파일
        두 파일을 따로 작성하는 경우도 있다.
    */

    // ItemEntity를 Item으로 바꿔주는 Mapper 아아아 대박

    override fun requestAllUser(): Flow<List<User>>  = flow{
        val items = retrofitService.getAllItem().map {
            mapperToDomain(it)
        }
        Log.d("RepositoryImpl", items.toString())
        emit(items)
    }

    override fun requestUser(login: String): Flow<User> = flow{
        val item = mapperToDomain(retrofitService.getItem(login))
        Log.d("RepositoryImpl", item.toString())
        getRepository("codingTest")
        emit(item)
    }

    private fun getRepository(repository: String){
        retrofitService.getRepository(repository).enqueue(object: Callback<RepositoryEntity>{
            override fun onResponse(call: Call<RepositoryEntity>, response: Response<RepositoryEntity>) {
                if(response.isSuccessful){
                    Log.d("getRepository", response.body().toString())
                    Log.d("getRepository", response.body().toString())
                }else{
                    Log.e("GR- not Successful", response.toString())
                }
            }

            override fun onFailure(call: Call<RepositoryEntity>, t: Throwable) {
                Log.d("getRepository Failure", t.toString())
            }
        })
    }
}