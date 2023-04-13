package com.example.cleanarchitecture.presentation.feature.main.followers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.databinding.ItemFollowersBinding

class FollowersAdapter(
    private val onClickUser: (UserInfo) -> Unit
): RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {
    private lateinit var context: Context

    inner class FollowersViewHolder(val binding: ItemFollowersBinding): ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding = ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.run {
            Glide.with(context).load(item.avatarUrl).into(userAvatar)
            userLoginText.text = item.login

            root.setOnClickListener {
                onClickUser(item)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.count()
}