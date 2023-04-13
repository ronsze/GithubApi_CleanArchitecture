package com.example.cleanarchitecture.presentation.feature.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.presentation.feature.base.BaseActivity
import com.example.cleanarchitecture.presentation.feature.main.favorite.FavoriteFragment
import com.example.cleanarchitecture.presentation.feature.main.followers.FollowersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModels()

    override fun initData() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setViewEvents()
    }

    override fun observeViewModel() {}

    private fun initView() {
        binding.bottomNavigation.selectedItemId = R.id.followers
        replaceFragment(FollowersFragment.newInstance())
    }

    private fun setViewEvents() {
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.followers -> FollowersFragment.newInstance()
                R.id.favorite -> FavoriteFragment.newInstance()
                else -> throw java.lang.Exception("")
            }

            replaceFragment(fragment)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.tag)
            .commit()
    }
}