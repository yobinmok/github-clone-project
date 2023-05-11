package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor (private val repository: UserRepository) {
    fun execute(login: String): Flow<User> {
        return repository.requestUser(login)
    }
}