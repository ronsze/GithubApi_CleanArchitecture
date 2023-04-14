package com.example.cleanarchitecture.domain.usecase.github.remote

import com.example.cleanarchitecture.domain.repository.github.GithubRepository

class GetUserInfoUseCase(private val githubRepository: GithubRepository) {
    fun execute(userLogin: String) = githubRepository.getUserInfo(userLogin)
}