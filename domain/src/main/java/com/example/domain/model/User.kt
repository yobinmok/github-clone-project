package com.example.domain.model

class User (
    val id: String,
    val avatarUrl: String,
    val login: String,
    val name: String?,
    val followers: String,
    val following: String,
    val bio: String?,
    val htmlUrl: String,
    val company: String?,
    val email: String?,
    val location: String?
)