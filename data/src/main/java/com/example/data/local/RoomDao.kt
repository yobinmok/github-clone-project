package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.RepositoryEntity
import com.example.data.model.UserEntity

@Dao
interface RoomDao {
// Room 작업은 main thread 에서 진행하면 오류가 발생한다.
// 꼭 suspend 함수로 선언해 코루틴 내에서 실행하기!

    // 리포지터리
//    @Query("SELECT * FROM RepositoryEntity")
//    suspend fun getAllRepository(): RepositoryEntity
//    @Insert(onConflict =  OnConflictStrategy.IGNORE)
//    suspend fun insertRepository()
//    @Delete
//    suspend fun deleteRepository()
//
//    // 사용자
    @Query("SELECT * FROM UserEntity")
    suspend fun getMyInfo(): UserEntity
//    @Insert(onConflict =  OnConflictStrategy.IGNORE)
//    suspend fun insertMyInfo()
//    @Delete
//    suspend fun deleteMyInfo()
}