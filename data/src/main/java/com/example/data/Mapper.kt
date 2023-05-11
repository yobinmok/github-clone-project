package com.example.data

import com.example.data.model.ItemEntity
import com.example.domain.Item

// UI(Domain) <-> Data 간의 Model 데이터 변환

// Data -> Domain 모델로 변환
fun mapperToDomainList(itemEntities: List<ItemEntity>): List<Item>{
    return itemEntities.toList().map {
        Item(
            it.id,
            it.avatarUrl,
            it.login
        )
    }
}

fun mapperToDomain(item: ItemEntity): Item{
    return Item(item.id, item.avatarUrl, item.login)
}

// Domain -> Data 모델로 변환
fun mapperToData(){

}