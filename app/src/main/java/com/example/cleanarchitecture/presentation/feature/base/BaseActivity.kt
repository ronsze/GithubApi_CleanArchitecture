package com.example.cleanarchitecture.presentation.feature.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        observeViewModel()
        observeGlobal()
    }

    abstract fun initData()
    abstract fun observeViewModel()

    private fun observeGlobal() {
        viewModel.showToastEvent.observe(this) {
            showToast(it)
        }
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}