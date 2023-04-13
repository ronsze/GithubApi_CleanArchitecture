package com.example.cleanarchitecture.presentation.feature.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        observeViewModel()
        observeGlobal()
        super.onCreate(savedInstanceState)
    }

    abstract fun observeViewModel()

    private fun observeGlobal() {
        viewModel.showToastEvent.observe(this) {
            showToast(it)
        }
    }

    protected fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}