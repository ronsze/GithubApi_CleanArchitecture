package com.example.cleanarchitecture.presentation.feature.main.followers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.databinding.FragmentFollowersBinding
import com.example.cleanarchitecture.presentation.feature.base.BaseFragment
import com.example.cleanarchitecture.presentation.feature.base.BaseViewModel
import com.example.cleanarchitecture.presentation.feature.userdetail.UserDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment: BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = FollowersFragment()
    }

    private lateinit var binding: FragmentFollowersBinding
    override val viewModel: FollowersViewModel by viewModels()

    private lateinit var adapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        viewModel.userInfo = requireActivity().intent.getSerializableExtra("user_info") as UserInfo
        initView()
        viewModel.loadFollowerList()
        return binding.root
    }

    override fun observeViewModel() {
        viewModel.followerListUpdateEvent.observe(this) {
            endProgress()
            adapter.differ.submitList(viewModel.followerList)
        }
    }

    private fun initView() {
        setViewEvents()
        adapter = FollowersAdapter(this::goToUserDetailActivity)
        binding.followersRecyclerView.adapter = adapter

        binding.followersRecyclerView.addOnScrollListener(scrollListener)
        binding.userLogin.text = viewModel.userInfo.login
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollHorizontally(1) && recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                viewModel.loadFollowerList()
            }
        }
    }

    private fun setViewEvents() {
        binding.backButton.setOnClickListener {
            requireActivity().finish()
        }

        binding.addFavoriteButton.setOnClickListener {
            viewModel.insertFavorite()
        }
    }

    private fun endProgress() {
        if (binding.progressBar.visibility == View.VISIBLE) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.followersRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun goToUserDetailActivity(userInfo: UserInfo) {
        val intent = Intent(requireActivity(), UserDetailActivity::class.java)
        intent.putExtra("user_login", userInfo.login)
        requireActivity().startActivity(intent)
    }
}