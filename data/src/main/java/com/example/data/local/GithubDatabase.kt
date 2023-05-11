package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.RepositoryEntity
import com.example.data.model.UserEntity

@Database(entities =  [UserEntity::class, RepositoryEntity::class], version = 1, exportSchema = false)
abstract class GithubDatabase: RoomDatabase() {
    abstract fun roomDao(): RoomDao
}

// Hilt를 적용하기 전에는 companion object로 싱글톤 db를 통해 생성했던 것과 다르게,
// Hilt에서는 모듈로 구현 후 사용하는 곳에서 inect한다.