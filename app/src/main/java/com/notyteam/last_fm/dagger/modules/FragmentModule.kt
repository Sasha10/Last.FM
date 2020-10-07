package com.notyteam.last_fm.dagger.modules

import com.notyteam.last_fm.base.BaseActivity
import com.notyteam.last_fm.base.BaseFragment
import com.notyteam.last_fm.base.mvvm.BaseViewModel
import com.notyteam.last_fm.dagger.scopes.FragmentScope
import com.notyteam.last_fm.utils.ciceron.CiceroneFragmentManager
import com.notyteam.last_fm.utils.ciceron.Screens
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.*

@Module
class FragmentModule(private val containerId: Int, private val baseActivity: BaseActivity<*>) {

    @Provides
    @FragmentScope
    fun provideBaseActivity(): BaseActivity<*> = baseActivity

    @Provides
    @FragmentScope
    fun provideCiceroneAppNavigator(): SupportAppNavigator {
        return object : SupportAppNavigator(baseActivity, containerId) {
            override fun applyCommands(commands: Array<Command>) {
                super.applyCommands(commands)
                baseActivity.supportFragmentManager.executePendingTransactions()
            }
        }
    }

    @Provides
    @FragmentScope
    fun provideFragmentManager(ciceroneNavigator: SupportAppNavigator) =
        object : CiceroneFragmentManager {

            override fun <P : BaseViewModel> replaceFragment(fragment: BaseFragment<P>) {
                ciceroneNavigator.applyCommands(arrayOf(Replace(Screens.FragmentScreen(fragment))))
            }

            override fun <P : BaseViewModel> addFragment(fragment: BaseFragment<P>) {
                ciceroneNavigator.applyCommands(arrayOf(Forward(Screens.FragmentScreen(fragment))))
            }

            override fun <P : BaseViewModel> newRootFragment(fragment: BaseFragment<P>) {
                ciceroneNavigator.applyCommands(
                    arrayOf(
                        BackTo(null),
                        Replace(Screens.FragmentScreen(fragment))
                    )
                )
            }

            override fun <P : BaseViewModel> backTo(fragment: BaseFragment<P>) {
                ciceroneNavigator
                ciceroneNavigator.apply { BackTo(Screens.FragmentScreen(fragment)) }
            }

            override fun exit() {
                ciceroneNavigator.applyCommands(arrayOf(Back()))
            }
        }
}