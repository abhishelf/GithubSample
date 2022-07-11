package com.abhishek.githubsample.repository

import com.abhishek.githubsample.network.NetworkService

class NetworkRepository constructor(private val retrofitService: NetworkService) {

    suspend fun getClosedPR(userId: String, repoName: String, status: String) =
        retrofitService.getClosedPR(userId, repoName, status)
}