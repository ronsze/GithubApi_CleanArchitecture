package com.example.cleanarchitecture.presentation.feature.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.data.room.favorite.FavoriteEntity
import com.example.cleanarchitecture.domain.usecase.github.local.GetFavoriteByLoginUseCase
import com.example.cleanarchitecture.domain.usecase.github.remote.GetUserInfoUseCase
import com.example.cleanarchitecture.domain.usecase.github.local.InsertFavoriteUseCase
import com.example.cleanarchitecture.presentation.feature.base.BaseViewModel
import com.example.cleanarchitecture.presentation.feature.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getFavoriteByLoginUseCase: GetFavoriteByLoginUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase
): BaseViewModel() {
    private val _loadUserInfoDoneEvent = SingleLiveEvent<Void>()
    val loadUserInfoDoneEvent: LiveData<Void> get() = _loadUserInfoDoneEvent

    lateinit var userInfo: UserInfo

    fun loadUserInfo(userLogin: String) {
        compositeDisposable.add(
            getUserInfoUseCase.execute(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    userInfo = res
                    _loadUserInfoDoneEvent.call()
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
                showToast("Favorite User Added")
            } else {
                showToast("Already Added")
            }
        }
    }
}