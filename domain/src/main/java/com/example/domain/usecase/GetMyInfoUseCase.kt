package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.MyInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(private val repository: MyInfoRepository) {

    fun getMyInfoExecute(): Flow<User> {
        return repository.getMyInfo()
    }
    fun insertExecute(user: User){//: Flow<Boolean>{
        repository.insertMyInfo(user)
    }
    fun deleteExecute(){
        repository.deleteMyInfo()
    }

}