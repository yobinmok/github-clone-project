package com.example.domain.usecase

import com.example.domain.model.SearchUser
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// @Scope 지정: GetItemUseCase 인스턴스 사용 범위
class GetAllUserUseCase @Inject constructor(private val repository: UserRepository) {

    val userListRequest = repository.userListResult
    fun execute(username: String): Flow<List<SearchUser>> {
        return repository.requestUserList(username)
    }
}