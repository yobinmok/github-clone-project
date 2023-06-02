package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface MyInfoRepository {

    fun getMyInfo(): Flow<User>
    fun insertMyInfo(user: User)//: Flow<Boolean>
    fun deleteMyInfo()
}