package com.example.domain.repository

import com.example.domain.NetworkResult
import com.example.domain.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LikedRepository {

    val likedRepoResult: StateFlow<NetworkResult>
    fun requestLikedRepo(): Flow<List<Repository>>
    fun insertLikedRepo(repo: Repository)
    fun deleteLikedRepo(repo: Repository)
}