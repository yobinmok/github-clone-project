package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.RepositoryEntity
import com.example.data.model.UserEntity
import com.example.domain.model.Repository
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    // 리포지터리
    @Query("SELECT * FROM RepositoryEntity")
    suspend fun getAllRepository(): List<RepositoryEntity>

    @Query("SELECT * FROM RepositoryEntity WHERE :id = id")
    suspend fun getLikedRepository(id: Int): RepositoryEntity?

    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insertRepository(repo: RepositoryEntity)
    @Delete
    suspend fun deleteRepository(repo: RepositoryEntity)
//
//    // 사용자
    @Query("SELECT * FROM UserEntity")
    suspend fun getMyInfo(): UserEntity
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insertMyInfo(user: UserEntity): Long
    @Query("DELETE FROM UserEntity")
    suspend fun deleteMyInfo()
}