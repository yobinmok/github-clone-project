package com.example.domain

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getAllItem(): Flow<List<Item>>
    fun getItem(login: String): Flow<Item>
}