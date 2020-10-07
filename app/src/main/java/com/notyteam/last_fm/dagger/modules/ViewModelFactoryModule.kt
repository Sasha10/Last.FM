package com.notyteam.last_fm.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.notyteam.last_fm.base.mvvm.DaggerViewModelFactory
import com.notyteam.last_fm.dagger.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    @FragmentScope
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}