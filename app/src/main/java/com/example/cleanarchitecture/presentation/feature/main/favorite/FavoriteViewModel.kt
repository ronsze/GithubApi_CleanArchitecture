package com.example.cleanarchitecture.presentation.feature.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.room.favorite.FavoriteEntity
import com.example.cleanarchitecture.domain.usecase.github.GetFavoriteListUseCase
import com.example.cleanarchitecture.presentation.feature.base.BaseViewModel
import com.example.cleanarchitecture.presentation.feature.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteListUseCase: GetFavoriteListUseCase
): BaseViewModel() {
    private val _loadFavoriteListEvent = SingleLiveEvent<Void>()
    val loadFavoriteListEvent: LiveData<Void> get() = _loadFavoriteListEvent

    val favoriteList = ArrayList<FavoriteEntity>()

    fun loadFavoriteList() {
        viewModelScope.launch(Dispatchers.Main) {
            val list = withContext(Dispatchers.IO) {
                getFavoriteListUseCase.execute()
            }
            favoriteList.addAll(list)
            _loadFavoriteListEvent.call()
        }
    }
}