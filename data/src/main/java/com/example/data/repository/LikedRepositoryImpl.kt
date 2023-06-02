package com.example.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.data.likedRepoMapperToData
import com.example.data.likedRepoMapperToDomain
import com.example.data.local.GithubDatabase
import com.example.data.repoListMapperToDomain
import com.example.domain.NetworkResult
import com.example.domain.model.Repository
import com.example.domain.repository.LikedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LikedRepositoryImpl @Inject constructor(private val githubDatabase: GithubDatabase) :
    LikedRepository {

    private var _likedRepoResult = MutableStateFlow(NetworkResult.LOADING)
    override val likedRepoResult: StateFlow<NetworkResult> get() = _likedRepoResult

    @RequiresApi(Build.VERSION_CODES.O)
    override fun requestLikedRepo(): Flow<List<Repository>> = callbackFlow {
        _likedRepoResult.value = NetworkResult.LOADING
        CoroutineScope(Dispatchers.IO).launch{
            val items = githubDatabase.roomDao().getAllRepository().map { entity ->
                likedRepoMapperToDomain(entity)
            }
            Log.d("requestLikedRepo", items.toString())
            if(items.isEmpty()) _likedRepoResult.value = NetworkResult.NOT_SUCCESS
            else _likedRepoResult.value = NetworkResult.SUCCESS

            trySend(items)
        }
        awaitClose{ }
    }

    override fun insertLikedRepo(repo: Repository) {
        val repoEntity = likedRepoMapperToData(repo)
        CoroutineScope(Dispatchers.IO).launch {
            githubDatabase.roomDao().insertRepository(repoEntity)
        }
    }

    override fun deleteLikedRepo(repo: Repository) {
        val repoEntity = likedRepoMapperToData(repo)
        CoroutineScope(Dispatchers.IO).launch {
            githubDatabase.roomDao().deleteRepository(repoEntity)
        }
    }
}