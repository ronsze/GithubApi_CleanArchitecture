package com.example.cleanarchitecture.presentation.feature.main.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.data.room.favorite.FavoriteEntity
import com.example.cleanarchitecture.domain.usecase.github.local.GetFavoriteByLoginUseCase
import com.example.cleanarchitecture.domain.usecase.github.remote.GetFollowerListUseCase
import com.example.cleanarchitecture.domain.usecase.github.local.InsertFavoriteUseCase
import com.example.cleanarchitecture.presentation.feature.base.BaseViewModel
import com.example.cleanarchitecture.presentation.feature.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val getFollowerListUseCase: GetFollowerListUseCase,
    private val getFavoriteByLoginUseCase: GetFavoriteByLoginUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : BaseViewModel() {
    companion object {
        val TAG = FollowersViewModel::class.java.simpleName
    }

    private val _followerListUpdateEvent = SingleLiveEvent<Void>()
    val followerListUpdateEvent: LiveData<Void> get() = _followerListUpdateEvent

    val followerList = ArrayList<UserInfo>()

    lateinit var userInfo: UserInfo
    private var page = 1

    fun loadFollowerList() {
        compositeDisposable.add(
            getFollowerListUseCase.execute(userInfo.login, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    followerList.addAll(res)
                    page++
                    _followerListUpdateEvent.call()
                }, { err ->
                    showToast(err.message.toString())
                })
        )
    }

    fun insertFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorite = getFavoriteByLoginUseCase.execute(userInfo.login)
            if (favorite == null) {
                val entity = FavoriteEntity(userInfo.login, userInfo.avatarUrl)
                insertFavoriteUseCase.execute(entity)
                withContext(Dispatchers.Main) { showToast("Favorite User Added") }
            } else {
                withContext(Dispatchers.Main) { showToast("Already Added") }
            }
        }
    }
}