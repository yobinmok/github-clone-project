package com.example.data.repository

import com.example.data.local.GithubDatabase
import javax.inject.Inject

class LikedRepositoryImpl @Inject constructor(private val githubDatabase: GithubDatabase) {

}