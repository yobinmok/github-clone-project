package com.example.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NetworkResult
import com.example.domain.model.Repository
import com.example.domain.model.SearchUser
import com.example.domain.usecase.GetLikedUseCase
import com.example.domain.usecase.GetRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(private val getRepoUseCase: GetRepoUseCase, private val getLikedUseCase: GetLikedUseCase): ViewModel() {

    private val _repoList = MutableStateFlow<List<Repository>>(listOf())
    val repoList: StateFlow<List<Repository>> get() = _repoList

    private val _likedRepoList = MutableStateFlow<List<Repository>>(listOf())
    val likedRepoList: StateFlow<List<Repository>> get() = _likedRepoList

    val repoResult = getRepoUseCase.repoListRequest
    val likedRepoResult = getLikedUseCase.likedRepoResult
    private val _emptyMessage = MutableStateFlow<String>("")
    val emptyMessage: StateFlow<String> get() = _emptyMessage

    fun requestRepoList(reponame: String){
        viewModelScope.launch {
            getRepoUseCase.execute(reponame).collect{ repoList->
                _repoList.value = repoList
                Log.d("requestList", repoList.toString())
            }
        }
    }

    fun requestLikedRepo(){
        viewModelScope.launch {
            getLikedUseCase.getListExecute().collect{
                if(it.isEmpty()) {
                    _emptyMessage.value = "관심 표시한 리포지터리가 없습니다."
                }
                else {
                    _emptyMessage.value = ""
                    _likedRepoList.value = it
                }
            }
        }
    }

    fun setLiked(repo: Repository){
        if (repo.liked) { // 취소
            getLikedUseCase.deleteLikedExecute(repo)
        }
        else{ // 찜하기
            getLikedUseCase.insertLikedExecute(repo)
        }
    }
}