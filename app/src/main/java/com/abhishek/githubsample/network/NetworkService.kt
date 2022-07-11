package com.abhishek.githubsample.network

import com.abhishek.githubsample.model.PullRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("repos/{userName}/{repoName}/pulls")
    suspend fun getClosedPR(
        @Path("userName") userName: String,
        @Path("repoName") repoName: String,
        @Query("state") state: String
    ): Response<ArrayList<PullRequest>>
}