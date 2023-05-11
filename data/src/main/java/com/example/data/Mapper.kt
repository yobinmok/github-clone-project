package com.example.data

import com.example.data.model.SearchUserEntity
import com.example.domain.model.User

// UI(Domain) <-> Data 간의 Model 데이터 변환

// Data -> Domain 모델로 변환
fun mapperToDomainList(itemEntities: List<SearchUserEntity>): List<User>{
    return itemEntities.toList().map {
        User(
            it.id,
            it.avatarUrl,
            it.login
        )
    }
}

fun mapperToDomain(item: SearchUserEntity): User {
    return User(item.id, item.avatarUrl, item.login)
}

// Domain -> Data 모델로 변환
fun mapperToData(){

}