package com.abhishek.githubsample.model

import com.google.gson.annotations.SerializedName

class PullRequest(
    @SerializedName("title") var title: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("closed_at") var closedAt: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("user") var user: User? = User(),
)
