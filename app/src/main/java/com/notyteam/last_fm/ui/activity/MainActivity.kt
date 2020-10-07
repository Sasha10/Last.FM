package com.notyteam.last_fm.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.notyteam.last_fm.R
import com.notyteam.last_fm.base.BaseActivity
import com.notyteam.last_fm.ui.fragments.main.MainFragment
import com.notyteam.last_fm.ui.viewmodels.GeoArtistViewModel
import com.notyteam.last_fm.utils.injectViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import noty_team.com.masterovik.base.base_action_bar.ActionBarContract
import noty_team.com.masterovik.base.base_action_bar.BaseActionBarPresenter
import noty_team.com.masterovik.base.base_action_bar.BaseActionBarView


class MainActivity : BaseActivity<GeoArtistViewModel>() {
    private lateinit var actionBarView: ActionBarContract.View
    private lateinit var actionBarPresenter: ActionBarContract.Presenter

    companion object {
        fun start(baseActivity: Activity) {
            baseActivity.startActivity(
                Intent(baseActivity, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
            Animatoo.animateZoom(baseActivity)
        }
    }

    override fun layout() = R.layout.activity_main

    override fun initialization() {

        actionBarView = BaseActionBarView(toolbar)
        actionBarPresenter = BaseActionBarPresenter(actionBarView)
        fragmentManager.replaceFragment(MainFragment())
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): GeoArtistViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun getActionBarView(): ActionBarContract.View? = actionBarView

}