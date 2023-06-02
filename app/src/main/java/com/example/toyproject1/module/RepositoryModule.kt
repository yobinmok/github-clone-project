package com.example.toyproject1.module

import com.example.data.repository.LikedRepositoryImpl
import com.example.data.repository.MyInfoRepositoryImpl
import com.example.data.repository.RepoRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.LikedRepository
import com.example.domain.repository.MyInfoRepository
import com.example.domain.repository.RepoRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.hilt.*
import dagger.Module
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// 모듈을 사용하거나 설치할 Android 클래스
@InstallIn(SingletonComponent::class) // Application 전체에서 사용할 수 있도록 -> 아래 함수에도 @Singleton
@Module
abstract class RepositoryModule {

    @Singleton // Hilt로 제공하는 컴포넌트가 Application 범위까지 존재함
    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindRepoRepository(repositoryImpl: RepoRepositoryImpl): RepoRepository

    @Singleton
    @Binds
    abstract fun bindLikedRepository(repositoryImpl: LikedRepositoryImpl): LikedRepository

    @Singleton
    @Binds
    abstract fun bindMyInfoRepository(repositoryImpl: MyInfoRepositoryImpl): MyInfoRepository
}
