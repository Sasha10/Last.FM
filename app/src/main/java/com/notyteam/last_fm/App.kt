package com.notyteam.last_fm

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.droidnet.DroidNet
import com.notyteam.last_fm.dagger.components.AppComponent
import com.notyteam.last_fm.dagger.components.DaggerAppComponent
import com.notyteam.last_fm.dagger.modules.ApiModule
import com.notyteam.last_fm.dagger.modules.AppModule
import io.paperdb.Paper

class App : Application() {
    companion object {
        operator fun get(activity: Activity): App {
            return activity.application as App
        }

        operator fun get(context: Context): App {
            return context as App
        }
    }

    val appComponent: AppComponent by lazy {
        initDagger()
    }

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .build()

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        DroidNet.init(this)
        Paper.init(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners()
    }
}