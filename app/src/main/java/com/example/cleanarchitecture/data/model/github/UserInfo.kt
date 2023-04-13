package com.example.cleanarchitecture.data.model.github

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("bio")
    val bio: String,

    @SerializedName("public_repos")
    val publicRepos: Int,

    @SerializedName("public_gits")
    val publicGits: Int,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int
): java.io.Serializable