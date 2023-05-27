package com.example.domain.usecase

import com.example.domain.model.Repository
import com.example.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepoUseCase @Inject constructor(private val repository: RepoRepository) {

    val repoListRequest = repository.repoListResult

    fun execute(reponame: String): Flow<List<Repository>> {
        return repository.requestRepoList(reponame)
    }
}