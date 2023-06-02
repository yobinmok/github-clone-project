package com.example.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.model.RepositoryEntity
import com.example.data.model.SearchUserEntity
import com.example.data.model.UserEntity
import com.example.domain.model.Repository
import com.example.domain.model.SearchUser
import com.example.domain.model.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// UI(Domain) <-> Data 간의 Model 데이터 변환

// Data -> Domain 모델로 변환
fun userListMapperToDomain(item: SearchUserEntity): SearchUser {
    return SearchUser(item.login, item.id, item.avatarUrl)
}

fun userMapperToDomain(item: UserEntity): User {
    return User(item.id, item.avatarUrl, item.login, item.name, item.followers,
        item.following, item.bio, item.htmlUrl, item.company, item.email, item.location)
}

fun userMapperToData(item: User): UserEntity{
    return UserEntity(item.id, item.avatarUrl, item.login, item.name, item.followers,
    item.following, item.bio, item.htmlUrl, item.company, item.email, item.location)
}

@RequiresApi(Build.VERSION_CODES.O)
fun repoListMapperToDomain(item: RepositoryEntity, isLiked: Boolean): Repository{
    val owner = SearchUser(item.owner.login, item.owner.id, item.owner.avatarUrl)

    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val updateAt = LocalDateTime.parse(item.updatedAt, inputFormatter)

    return Repository(item.id, item.name, item.fullName, owner, item.description,
        item.htmlUrl, updateAt.format(outputFormatter), item.topics, item.star, item.language, item.forkCount, isLiked)
}

fun likedRepoMapperToDomain(item: RepositoryEntity): Repository{
    val owner = SearchUser(item.owner.login, item.owner.id, item.owner.avatarUrl)
    return Repository(item.id, item.name, item.fullName, owner, item.description,
        item.htmlUrl, item.updatedAt, item.topics, item.star, item.language, item.forkCount, true)
}

// Domain -> Data 모델로 변환
fun likedRepoMapperToData(item: Repository): RepositoryEntity{
    val owner = SearchUserEntity(item.owner.login, item.owner.id, item.owner.avatarUrl)
    return RepositoryEntity(item.id, item.name, item.fullName, owner, item.description, item.htmlUrl,
    item.updatedAt, item.topics, item.star, item.language, item.forkCount)
}