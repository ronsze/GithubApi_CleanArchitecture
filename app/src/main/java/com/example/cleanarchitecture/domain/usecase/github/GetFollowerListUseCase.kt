package com.example.cleanarchitecture.domain.usecase.github

import com.example.cleanarchitecture.domain.repository.github.GithubRepository

class GetFollowerListUseCase(private val githubRepository: GithubRepository) {
    fun execute(userLogin: String, page: Int) = githubRepository.getFollowerList(userLogin, page)
}