package com.notyteam.last_fm.utils.ciceron

import com.notyteam.last_fm.base.BaseFragment
import com.notyteam.last_fm.base.mvvm.BaseViewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class FragmentScreen<P : BaseViewModel, T : BaseFragment<P>>(var fragment: T) :
        SupportAppScreen() {
        override fun getFragment(): androidx.fragment.app.Fragment {
            return fragment
        }
    }
}