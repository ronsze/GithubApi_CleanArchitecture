package com.example.cleanarchitecture.presentation.feature.userdetail

import android.content.Intent
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.databinding.ActivityUserDetailBinding
import com.example.cleanarchitecture.presentation.feature.base.BaseActivity
import com.example.cleanarchitecture.presentation.feature.main.MainActivity
import com.example.cleanarchitecture.presentation.feature.userweb.UserWebActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity: BaseActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    override val viewModel: UserDetailViewModel by viewModels()

    override fun initData() {
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userLogin = intent.getStringExtra("user_login") ?: ""
        viewModel.loadUserInfo(userLogin)
    }

    override fun observeViewModel() {
        viewModel.loadUserInfoDoneEvent.observe(this) {
            initView()
        }
    }

    private fun initView() {
        with(viewModel.userInfo) {
            Glide.with(this@UserDetailActivity).load(avatarUrl).into(binding.userAvatar)
            binding.userLoginText.text = login
            binding.userNameText.text = name
            binding.userLoginText.text = location
            binding.followersValue.text = followers.toString()
            binding.followingValue.text = following.toString()
            binding.publicGitsValue.text = publicGits.toString()
            binding.publicReposValue.text = publicRepos.toString()
        }

        setViewEvents()
    }

    private fun setViewEvents() {
        binding.getFollowersButton.setOnClickListener {
            goToMainActivity()
        }

        binding.githubProfileButton.setOnClickListener {
            goToUserWebActivity()
        }

        binding.doneButton.setOnClickListener {
            finish()
        }

        binding.addButton.setOnClickListener {
            viewModel.insertFavorite()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user_info", viewModel.userInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun goToUserWebActivity() {
        val intent = Intent(this, UserWebActivity::class.java)
        intent.putExtra("user_login", viewModel.userInfo.login)
        startActivity(intent)
    }
}