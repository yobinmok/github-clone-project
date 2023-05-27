package com.example.presentation.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.domain.NetworkResult
import com.example.domain.model.SearchUser
import com.example.domain.model.User
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

    private val _searchUser = MutableStateFlow(User("","","", "", "",
        "", "", "", "", "", ""))
    val searchUser: StateFlow<User> get() = _searchUser

    private val _userValid = MutableStateFlow(true)
    val userValid: StateFlow<Boolean> get() = _userValid

    private val _userListValid = MutableStateFlow(true)
    val userListValid: StateFlow<Boolean> get() = _userListValid

    private val userResult = getUserUseCase.userResult
    private val userListResult = getAllUserUseCase.userListRequest

    fun requestUserList(username: String){
        viewModelScope.launch{
            getAllUserUseCase.execute(username).collect{ userList ->
                if(userList.isEmpty()){ // 해당 검색어가 포함된 사용자가 없음
//                    _userValid.value = false
                    _userListValid.value = false
                }else if (userList.size == 1){
                    if(userList[0].login == username){ // 일치 1명 -> 유저 보이고, 리스트 안보이게
                        _userListValid.value = false
                    }else{ // 불일치 1명 -> 유저 안보이고, 리스트 보이게
                        _userListValid.value = true
                        _searchUserList.value = userList
                    }
                }else{
                    _userListValid.value = true
                    _searchUserList.value = userList
                }
            }
        }
    }

    fun requestUser(login: String){
        viewModelScope.launch {
            getUserUseCase.execute(login).collect{user->
                Log.d("viewModel User", user.toString())
                _userValid.value = true
                _searchUser.value = user
            }
        }
        viewModelScope.launch{
            userResult.collect(){
                when(it){
                    NetworkResult.SUCCESS -> _userValid.value = true
                    NetworkResult.LOADING -> {}
                    else -> _userValid.value = false
                }
            }
        }
    }
}