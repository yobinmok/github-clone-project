package com.example.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.data.api.RetrofitService
import com.example.data.likedRepoMapperToData
import com.example.data.local.GithubDatabase
import com.example.data.model.RepositoryEntity
import com.example.data.model.SearchRepoEntity
import com.example.data.repoListMapperToDomain
import com.example.domain.NetworkResult
import com.example.domain.model.Repository
import com.example.domain.repository.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class RepoRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val dao: GithubDatabase
) : RepoRepository {

    private var _repoResult = MutableStateFlow(NetworkResult.LOADING)
    override val repoResult: StateFlow<NetworkResult> get() = _repoResult

    private var _repoListResult = MutableStateFlow(NetworkResult.LOADING)
    override val repoListResult: StateFlow<NetworkResult> get() = _repoListResult

    private var _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> get() = _isLiked


    override fun requestRepoList(reponame: String): Flow<List<Repository>> = callbackFlow {
        _repoListResult.value = NetworkResult.LOADING
        retrofitService.requestRepoList(reponame).enqueue(object : Callback<SearchRepoEntity> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<SearchRepoEntity>,
                response: Response<SearchRepoEntity>
            ) {
                if (response.isSuccessful) {
                    Log.d("requestRepoList", response.body()!!.items.toString())

                    CoroutineScope(Dispatchers.IO).launch {
                        checkLikedRepo(response.body()!!.items).collect {
                            trySend(it)
                        }
                    }
                    _repoListResult.value = NetworkResult.SUCCESS
                } else {
                    Log.e("repo List Not successful", response.toString())
                    _repoListResult.value = NetworkResult.NOT_SUCCESS
                }
            }

            override fun onFailure(call: Call<SearchRepoEntity>, t: Throwable) {
                Log.e("repo List Not successful", t.toString())
                _repoListResult.value = NetworkResult.FAIL
            }
        })
        awaitClose {
            // 'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.
        }
    }

    suspend fun checkLikedRepo(list: List<RepositoryEntity>): Flow<List<Repository>> = flow {
        val result = CoroutineScope(Dispatchers.IO).async {
            list.map { repositoryEntity ->
                val item = dao.roomDao().getLikedRepository(repositoryEntity.id)
                item?.let {
                    repoListMapperToDomain(repositoryEntity, true)
                } ?: kotlin.run {
                    repoListMapperToDomain(repositoryEntity, false)
                }
            }
        }
        emit(result.await())
    }
}