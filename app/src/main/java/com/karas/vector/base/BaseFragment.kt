package com.karas.vector.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.karas.vector.Application
import com.karas.vector.player.di.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment <T: ViewBinding>: DependencyInjectorFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    abstract fun bind(): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind()
        return binding.root
    }
}