package com.example.cleanarchitecture.presentation.feature.userweb

import androidx.activity.viewModels
import com.example.cleanarchitecture.databinding.ActivityUserWebBinding
import com.example.cleanarchitecture.presentation.feature.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserWebActivity: BaseActivity() {
    private lateinit var binding: ActivityUserWebBinding
    override val viewModel: UserWebViewModel by viewModels()

    override fun initData() {
        binding = ActivityUserWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userLogin = intent.getStringExtra("user_login") ?: ""
        binding.webView.loadUrl("https://github.com/$userLogin")
    }

    override fun observeViewModel() {

    }
}