package com.example.domain.repository

import com.example.domain.NetworkResult
import com.example.domain.model.SearchUser
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    val userResult: StateFlow<NetworkResult>
    val userListResult: StateFlow<NetworkResult>
    fun requestUserList(username: String): Flow<List<SearchUser>>
    fun requestUser(username: String): Flow<User>
}