package com.notyteam.last_fm.utils.ciceron

import com.notyteam.last_fm.base.BaseFragment
import com.notyteam.last_fm.base.mvvm.BaseViewModel

interface CiceroneFragmentManager {
    fun <P : BaseViewModel> replaceFragment(fragment: BaseFragment<P>)

    fun <P : BaseViewModel> addFragment(fragment: BaseFragment<P>)

    fun <P : BaseViewModel> newRootFragment(fragment: BaseFragment<P>)

    fun <P : BaseViewModel> backTo(fragment: BaseFragment<P>)

    fun exit()
}