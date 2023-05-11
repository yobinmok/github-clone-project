package com.example.toyproject1.module

import com.example.data.ItemRepositoryImpl
import com.example.domain.ItemRepository
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
    abstract fun bindRepository(repositoryImpl: ItemRepositoryImpl): ItemRepository
}