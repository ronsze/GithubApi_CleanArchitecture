package com.example.cleanarchitecture.presentation.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.data.model.github.UserInfo
import com.example.cleanarchitecture.domain.usecase.github.remote.GetUserInfoUseCase
import com.example.cleanarchitecture.presentation.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
): BaseViewModel() {
    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> get() = _userInfoLiveData

    fun searchUser(userLogin: String) {
        compositeDisposable.add(
            getUserInfoUseCase.execute(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    _userInfoLiveData.postValue(res)
                }, { err ->
                    showToast(err.message.toString())
                })
        )
    }
}