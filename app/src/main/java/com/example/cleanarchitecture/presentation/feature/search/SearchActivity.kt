package com.example.cleanarchitecture.presentation.feature.search

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.databinding.ActivitySearchBinding
import com.example.cleanarchitecture.presentation.feature.base.BaseActivity
import com.example.cleanarchitecture.presentation.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity() {
    private lateinit var binding: ActivitySearchBinding
    override val viewModel: SearchViewModel by viewModels()

    override fun initData() {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewEvents()
    }

    override fun observeViewModel() {
        viewModel.userInfoLiveData.observe(this) {
            goToMainActivity(it)
        }
    }

    private fun setViewEvents() {
        binding.searchButton.setOnClickListener {
            viewModel.searchUser(binding.searchEdit.text.toString())
        }
    }

    private fun goToMainActivity(userInfo: UserInfo) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user_info", userInfo)
        startActivity(intent)
    }
}