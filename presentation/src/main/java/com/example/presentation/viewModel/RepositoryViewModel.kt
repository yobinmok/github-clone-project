package com.example.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Repository
import com.example.domain.usecase.GetRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(private val getRepoUseCase: GetRepoUseCase): ViewModel() {

    private val _repoList = MutableStateFlow<List<Repository>>(listOf())
    val repoList: StateFlow<List<Repository>> get() = _repoList

    fun requestRepoList(reponame: String){
        viewModelScope.launch {
            getRepoUseCase.execute(reponame).collect{ repoList->
                _repoList.value = repoList
            }
        }
    }
}