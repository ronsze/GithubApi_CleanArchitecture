package com.example.cleanarchitecture.data.api.github

import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.domain.repository.github.GithubRepository
import io.reactivex.rxjava3.core.Single

class RemoteGithubDataSource (private val githubService: GithubService): GithubRepository {
    override fun getUserInfo(userLogin: String): Single<UserInfo> = githubService.getUserInfo(userLogin)
    override fun getFollowerList(userLogin: String, page: Int): Single<ArrayList<UserInfo>> = githubService.getFollowerList(userLogin, page)
}