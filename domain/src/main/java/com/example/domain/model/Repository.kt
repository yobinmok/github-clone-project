package com.example.domain.model


data class Repository (
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: String,
    val description: String?,
    val htmlUrl: String,
    val updatedAt: String,
    val topics: List<String>?,
    val star: String,
    val language: String?
)