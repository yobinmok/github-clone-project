package com.example.domain.usecase

import com.example.domain.model.Repository
import com.example.domain.repository.LikedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikedUseCase @Inject constructor (private val repository: LikedRepository) {

    val likedRepoResult = repository.likedRepoResult

    fun getListExecute():Flow<List<Repository>> { // Flow -> ViewModel에서 코루틴 내 호출
        return repository.requestLikedRepo()
    }

    fun insertLikedExecute(repo: Repository){
        repository.insertLikedRepo(repo)
    }

    fun deleteLikedExecute(repo: Repository){
        repository.deleteLikedRepo(repo)
    }
}