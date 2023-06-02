package com.example.domain.repository

import com.example.domain.NetworkResult
import com.example.domain.model.Repository
import com.example.domain.model.SearchUser
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RepoRepository {

    val repoResult: StateFlow<NetworkResult>
    val repoListResult: StateFlow<NetworkResult>
    fun requestRepoList(reponame: String): Flow<List<Repository>>
}