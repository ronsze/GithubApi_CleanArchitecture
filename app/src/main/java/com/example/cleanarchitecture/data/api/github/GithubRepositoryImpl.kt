package com.example.cleanarchitecture.data.api.github

import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.domain.repository.github.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl(private val remoteGithubDataSource: RemoteGithubDataSource): GithubRepository {
    override fun getUserInfo(userLogin: String): Single<UserInfo> = remoteGithubDataSource.getUserInfo(userLogin)
    override fun getFollowerList(userLogin: String, page: Int): Single<ArrayList<UserInfo>> = remoteGithubDataSource.getFollowerList(userLogin, page)
}