package com.notyteam.last_fm.dagger.components

import android.app.Application
import com.notyteam.last_fm.api.retfofit.BaseRepository
import com.notyteam.last_fm.api.retfofit.CallbackWrapper
import com.notyteam.last_fm.api.retfofit.apis.BaseApi
import com.notyteam.last_fm.base.mvvm.BaseViewModel
import com.notyteam.last_fm.dagger.modules.ApiModule
import com.notyteam.last_fm.dagger.modules.AppModule
import com.notyteam.last_fm.dagger.modules.FragmentModule
import com.notyteam.last_fm.dagger.modules.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ApiModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent {
    fun plus(fragmentModule: FragmentModule): FragmentComponent

    fun inject(app: Application)
    fun inject(target: CallbackWrapper<Any, Any>)
    fun inject(target: BaseApi)
    fun inject(target: BaseRepository)
    fun inject(target: BaseViewModel)
}