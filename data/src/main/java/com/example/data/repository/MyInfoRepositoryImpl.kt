package com.example.data.repository

import com.example.data.local.GithubDatabase
import javax.inject.Inject

class MyInfoRepositoryImpl @Inject constructor(private val githubDatabase: GithubDatabase) {

    // 여기서 코루틴 내 출력해서 데이터 제공하셔요
    suspend fun requestMyInfo(){
        githubDatabase.roomDao().getMyInfo()
    }
}