package com.abhishek.githubsample.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var userName: String? = null,
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
)