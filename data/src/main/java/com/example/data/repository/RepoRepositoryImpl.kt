package com.example.data.repository

import android.util.Log
import com.example.data.api.RetrofitService
import com.example.data.model.SearchRepoEntity
import com.example.data.repoListMapperToDomain
import com.example.domain.NetworkResult
import com.example.domain.model.Repository
import com.example.domain.repository.RepoRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService): RepoRepository {

    private val initResult = NetworkResult.LOADING

    private var _repoResult = MutableStateFlow(initResult)
    override val repoResult: StateFlow<NetworkResult> get() = _repoResult

    private var _repoListResult = MutableStateFlow(initResult)
    override val repoListResult: StateFlow<NetworkResult> get() = _repoListResult

    override fun requestRepoList(reponame: String): Flow<List<Repository>> = callbackFlow {
        retrofitService.requestRepoList(reponame).enqueue(object: Callback<SearchRepoEntity> {
            override fun onResponse(
                call: Call<SearchRepoEntity>,
                response: Response<SearchRepoEntity>
            ) {
                if(response.isSuccessful){
                    Log.d("requestRepoList", response.body()!!.items.toString())
                    val items = response.body()!!.items.map{
                        repoListMapperToDomain(it)
                    }
                    _repoListResult.value = NetworkResult.SUCCESS
                    trySend(items)
                }else{
                    Log.e("repo List Not successful", response.toString())
                    _repoListResult.value = NetworkResult.NOT_SUCCESS
                }
            }

            override fun onFailure(call: Call<SearchRepoEntity>, t: Throwable) {
                Log.e("repo List Not successful", t.toString())
                _repoListResult.value = NetworkResult.FAIL
            }
        })
        awaitClose{
            // 'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.
        }
    }

    override fun requestRepo(reponame: String): Flow<Repository> {
        TODO("Not yet implemented")
    }


}