package com.example.cleanarchitecture.domain.repository.github

import com.example.cleanarchitecture.data.model.github.UserInfo
import io.reactivex.rxjava3.core.Single

interface GithubRepository {
    fun getUserInfo(userLogin: String): Single<UserInfo>
    fun getFollowerList(userLogin: String, page: Int): Single<ArrayList<UserInfo>>
}