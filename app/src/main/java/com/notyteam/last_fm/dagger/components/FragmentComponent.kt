package com.notyteam.last_fm.dagger.components

import com.notyteam.last_fm.base.BaseActivity
import com.notyteam.last_fm.base.BaseFragment
import com.notyteam.last_fm.base.mvvm.BaseViewModel
import com.notyteam.last_fm.dagger.modules.FragmentModule
import com.notyteam.last_fm.dagger.modules.MainViewModule
import com.notyteam.last_fm.dagger.scopes.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [FragmentModule::class,
        MainViewModule::class]
)
interface FragmentComponent {
    fun inject(target: BaseActivity<BaseViewModel>)
    fun inject(target: BaseFragment<BaseViewModel>)
}