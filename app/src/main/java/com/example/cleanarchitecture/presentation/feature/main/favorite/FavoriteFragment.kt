package com.example.cleanarchitecture.presentation.feature.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cleanarchitecture.databinding.FragmentFavoriteBinding
import com.example.cleanarchitecture.presentation.feature.base.BaseFragment
import com.example.cleanarchitecture.presentation.feature.main.followers.FollowersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }

    private lateinit var binding: FragmentFavoriteBinding
    override val viewModel: FavoriteViewModel by viewModels()

    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        initView()
        viewModel.loadFavoriteList()
        return binding.root
    }

    override fun observeViewModel() {
        viewModel.loadFavoriteListEvent.observe(this) {
            adapter.differ.submitList(viewModel.favoriteList)
        }
    }

    private fun initView() {
        adapter = FavoriteAdapter()
        binding.favoriteRecyclerView.adapter = adapter
    }
}