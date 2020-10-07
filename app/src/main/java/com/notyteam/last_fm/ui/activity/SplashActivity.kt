package com.notyteam.last_fm.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.notyteam.last_fm.BuildConfig
import com.notyteam.last_fm.R
import com.notyteam.last_fm.base.BaseActivity
import com.notyteam.last_fm.ui.viewmodels.GeoArtistViewModel
import com.notyteam.last_fm.utils.injectViewModel
import io.paperdb.Paper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*
import noty_team.com.masterovik.base.base_action_bar.ActionBarContract
import java.util.concurrent.TimeUnit


class SplashActivity : BaseActivity<GeoArtistViewModel>() {
    override fun layout() = R.layout.activity_splash

    companion object {
        fun start(baseActivity: Activity) {
            baseActivity.startActivity(
                Intent(baseActivity, SplashActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
    }

    override fun initialization() {

        emulateWaitTimer()
    }

    @SuppressLint("CheckResult")
    private fun emulateWaitTimer() {

        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                MainActivity.start(this)
                finish()
            }
        if (BuildConfig.DEV){
            loader.setIndicatorColor(R.color.colorPrimaryDev)
        }else{
            loader.setIndicatorColor(R.color.colorPrimaryProd)
        }
        Paper.book().write("switch","true")
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): GeoArtistViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun getActionBarView(): ActionBarContract.View? = null
}