package com.example.domain.model

import java.io.Serializable


data class Repository (
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: SearchUser,
    val description: String?,
    val htmlUrl: String,
    val updatedAt: String,
    val topics: List<String>?,
    val star: String,
    val language: String?,
    val forkCount: String?,
    var liked: Boolean
): Serializable