package com.karas.vector.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.karas.vector.Application

open class DependencyInjectorFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Application.appComponent?.inject(this)
    }
}