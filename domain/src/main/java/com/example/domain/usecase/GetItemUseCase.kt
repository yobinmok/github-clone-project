package com.example.domain.usecase

import com.example.domain.Item
import com.example.domain.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemUseCase @Inject constructor (private val repository: ItemRepository) {
    fun execute(login: String): Flow<Item> {
        return repository.getItem(login)
    }
}