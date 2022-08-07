package com.karas.vector.player.di.modules

import android.content.Context
import com.karas.vector.player.presentation.PlayerViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {
    @Provides
    @Singleton
    fun providePresentationViewModel() : PlayerViewModel{
        return PlayerViewModel()
    }
}