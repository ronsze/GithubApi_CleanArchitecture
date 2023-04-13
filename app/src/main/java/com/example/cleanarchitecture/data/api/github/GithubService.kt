package com.example.cleanarchitecture.data.api.github

import com.example.cleanarchitecture.data.model.github.UserInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("{userLogin}")
    fun getUserInfo(
        @Path("userLogin") userLogin: String
    ): Single<UserInfo>

    @GET("{userLogin}/followers?per_page=20&")
    fun getFollowerList(
        @Path("userLogin") userLogin: String,
        @Query("page") page: Int
    ): Single<ArrayList<UserInfo>>
}