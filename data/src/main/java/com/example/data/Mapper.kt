package com.example.data

import com.example.data.model.SearchUserEntity
import com.example.domain.model.SearchUser

// UI(Domain) <-> Data 간의 Model 데이터 변환

// Data -> Domain 모델로 변환
fun mapperToDomainList(itemEntities: List<SearchUserEntity>): List<SearchUser>{
    return itemEntities.toList().map {
        SearchUser(
            it.id,
            it.avatarUrl,
            it.login
        )
    }
}

fun mapperToDomain(item: SearchUserEntity): SearchUser {
    return SearchUser(item.id, item.avatarUrl, item.login)
}

// Domain -> Data 모델로 변환
fun mapperToData(){

}