package com.example.presentation.viewModel

import androidx.lifecycle.*
import com.example.domain.model.SearchUser
import com.example.domain.usecase.GetAllUserUseCase
import com.example.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val getAllUserUseCase: GetAllUserUseCase, private val getUserUseCase: GetUserUseCase): ViewModel() {

    private val _searchUserList = MutableStateFlow<List<SearchUser>>(listOf())
    val searchUserList: StateFlow<List<SearchUser>> get() = _searchUserList

    // 안사용 -> user(User) 사용
    private val _searchUserData = MutableStateFlow(SearchUser("","",""))
    val searchUserData: StateFlow<SearchUser> get() = _searchUserData

    fun requestUserList(username: String){
        viewModelScope.launch{
            getAllUserUseCase.execute(username).collect{
                _searchUserList.value = it
            }
        }
    }

    fun requestData(login: String){
        viewModelScope.launch {
            getUserUseCase.execute(login).collect{
                _searchUserData.value = it
            }
        }
    }
}