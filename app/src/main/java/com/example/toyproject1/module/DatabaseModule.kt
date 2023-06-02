package com.example.toyproject1.module

import android.content.Context
import androidx.room.Room
import com.example.data.local.GithubDatabase
import com.example.data.local.MyTypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// 외부 라이브러리 -> @Provides
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

//    @Provides
//    fun provideMoshiObject(): Moshi {
//        return Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext ctx: Context, moshi: Moshi): GithubDatabase {
        return Room.databaseBuilder(
            ctx, GithubDatabase::class.java,
            "github_database"
        ).fallbackToDestructiveMigration()
            .addTypeConverter(MyTypeConverter(moshi))
            .build()
    }

    @Provides
    fun provideUserDao(githubDatabase: GithubDatabase) = githubDatabase.roomDao()
}