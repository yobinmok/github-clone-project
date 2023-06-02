package com.example.data.repository

import com.example.data.local.GithubDatabase
import com.example.data.userMapperToData
import com.example.data.userMapperToDomain
import com.example.domain.model.User
import com.example.domain.repository.MyInfoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(private val githubDatabase: GithubDatabase): MyInfoRepository {

    // 여기서 코루틴 내 출력해서 데이터 제공하셔요
    override fun getMyInfo(): Flow<User> = callbackFlow{
        CoroutineScope(Dispatchers.IO).launch {
            val item = userMapperToDomain(githubDatabase.roomDao().getMyInfo())
            trySend(item)
        }
        awaitClose {  }
    }

    override fun insertMyInfo(user: User){//: Flow<Boolean> = flow{
        val infoUser = userMapperToData(user)
        CoroutineScope(Dispatchers.IO).launch {
            githubDatabase.roomDao().insertMyInfo(infoUser)
        }
    }

    override fun deleteMyInfo() {
        CoroutineScope(Dispatchers.IO).launch{
            githubDatabase.roomDao().deleteMyInfo()
        }
    }
}