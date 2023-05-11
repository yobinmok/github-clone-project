package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun requestAllUser(): Flow<List<User>>
    fun requestUser(login: String): Flow<User>
}