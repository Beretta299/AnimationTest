package com.karas.vector.player.di

import com.karas.vector.base.DependencyInjectorFragment
import com.karas.vector.player.di.modules.AppModule
import com.karas.vector.player.di.viewmodel.ViewModelModule
import com.karas.vector.player.presentation.PlayerFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class, ViewModelModule::class]
)
@Singleton
interface AppComponent {
    fun inject(baseFragment: DependencyInjectorFragment)
    fun inject(playerFragment: PlayerFragment)
}