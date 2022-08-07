package com.karas.vector

import android.app.Application
import com.karas.vector.player.di.AppComponent
import com.karas.vector.player.di.DaggerAppComponent
import com.karas.vector.player.di.modules.AppModule

class Application : Application() {


    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this.applicationContext))
            .build()
    }
    companion object{
        var appComponent: AppComponent? = null
            private set
    }
}