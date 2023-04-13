package com.example.cleanarchitecture.presentation.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    private val _showToastEvent = SingleLiveEvent<String>()
    val showToastEvent: LiveData<String> get() = _showToastEvent

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun showToast(msg: String) {
        _showToastEvent.postValue(msg)
    }
}