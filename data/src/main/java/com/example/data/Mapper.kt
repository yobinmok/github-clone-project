package com.example.data

import com.example.data.model.RepositoryEntity
import com.example.data.model.SearchUserEntity
import com.example.data.model.UserEntity
import com.example.domain.model.Repository
import com.example.domain.model.SearchUser
import com.example.domain.model.User

// UI(Domain) <-> Data 간의 Model 데이터 변환

// Data -> Domain 모델로 변환
fun userListMapperToDomain(item: SearchUserEntity): SearchUser {
    return SearchUser(item.id, item.avatarUrl, item.login)
}

fun userMapperToDomain(item: UserEntity): User {
    return User(item.id, item.avatarUrl, item.login, item.name, item.followers,
        item.following, item.bio, item.htmlUrl, item.company, item.email, item.location)
}

fun repoListMapperToDomain(item: RepositoryEntity): Repository{
    return Repository(item.id, item.name, item.fullName, item.owner.login, item.description,
        item.htmlUrl, item.updatedAt, item.topics, item.star, item.language)
}

// Domain -> Data 모델로 변환
fun mapperToData(){

}