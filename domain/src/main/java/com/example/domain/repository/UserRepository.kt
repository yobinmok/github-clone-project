package com.example.domain.repository

import com.example.domain.model.SearchUser
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun requestUser2(login: String): Flow<SearchUser>
    fun requestUserList(username: String): Flow<List<SearchUser>>
    fun requestUser(username: String): Flow<User>
}