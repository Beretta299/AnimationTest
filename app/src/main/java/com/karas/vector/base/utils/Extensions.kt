package com.karas.vector.base.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val viewModel = ViewModelProvider(this, factory).get(T::class.java)
    viewModel.body()
    return viewModel
}