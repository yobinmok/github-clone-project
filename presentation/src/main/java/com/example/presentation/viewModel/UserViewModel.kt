package com.example.presentation.viewModel

import androidx.lifecycle.*
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

    private val _allData = MutableStateFlow<List<User>>(listOf())
    val allData: StateFlow<List<User>> get() = _allData

    private val _userData = MutableStateFlow(User("","",""))
    val userData: StateFlow<User> get() = _userData

    fun requestAllData(){
        viewModelScope.launch{
            getAllUserUseCase.execute().collect{
                _allData.value = it
            }
        }
    }

    fun requestData(login: String){
        viewModelScope.launch {
            getUserUseCase.execute(login).collect{
                _userData.value = it
            }
        }
    }
}