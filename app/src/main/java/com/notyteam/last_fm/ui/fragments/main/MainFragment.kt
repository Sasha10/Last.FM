package com.notyteam.last_fm.ui.fragments.main

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.noty_team.damfastore.utils.paper.PaperIO
import com.notyteam.last_fm.BuildConfig
import com.notyteam.last_fm.R
import com.notyteam.last_fm.base.BaseFragment
import com.notyteam.last_fm.ui.adapter.artist.TopArtistAdapter
import com.notyteam.last_fm.ui.fragments.albums.TopAlbumsFragment
import com.notyteam.last_fm.ui.viewmodels.GeoArtistViewModel
import com.notyteam.last_fm.utils.*
import io.paperdb.Paper
import kotlinx.android.synthetic.main.ab_switch.*
import kotlinx.android.synthetic.main.fragment_main.*
import noty_team.com.masterovik.ui.adapters.items_adapter.ArtistItemLocal


class MainFragment : BaseFragment<GeoArtistViewModel>() {

    private lateinit var topArtistAdapter:  TopArtistAdapter
    var imageArtistUrl = ""
    var artist = PaperIO.artist
    override fun layout() = R.layout.fragment_main

    val countryArtist = arrayListOf("UKRAINE", "SPAIN", "GERMANY")

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): GeoArtistViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

            initToolbar()

    }

    private fun getListTopArtist(country: String) {
        isShowLoadingDialog(true)
        listenUpdates(country)
    }

    private fun listenUpdates(country: String) {

        viewModel.getTopArtist(country).observe(this) { response ->
            isShowLoadingDialog(false)
            when (response) {
                is Success -> {
                    PaperIO.artist = response.value.topArtists!!.map()
                    initTopArtistAdapter(response.value.topArtists!!.map())
                }
                is Failure -> {
                    showErrorSnack(response.errorMessage)
                }
            }
        }
    }

    private fun initTopArtistAdapter(arrayList: ArrayList<ArtistItemLocal>) {
        recycler_programs.layoutManager = GridLayoutManager(baseActivity, 2)
        topArtistAdapter = TopArtistAdapter(baseContext, arrayList) {
            it.image?.filter { it?.size == "large" }?.forEach {
                imageArtistUrl = it?.text.toString()
            }
            fragmentManager.addFragment(TopAlbumsFragment.newInstance(imageArtistUrl, it.name,it.mbid))
        }
        recycler_programs.adapter = topArtistAdapter
        topArtistAdapter.sortList(true)
    }

    private fun initToolbar() {

        baseActivity.getActionBarView()?.showActionBar(true)
        baseActivity.getActionBarView()?.setupCenterText(getString(R.string.title_artist))
        baseActivity.getActionBarView()?.showLeftButton(false)
        baseActivity.getActionBarView()?.showSpinnerCountry(true)
        baseActivity.getActionBarView()?.setupSpinnerCountry(countryArtist)
        baseActivity.getActionBarView()?.getOnSpinnerCountryListener()?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (baseActivity.netIsConnected()){
                    getListTopArtist(parent?.getItemAtPosition(position).toString())
                }else{
                    if(artist.isNullOrEmpty()){
                        showErrorSnack(getString(R.string.error_no_intenet_connection))
                    }else{
                        initTopArtistAdapter(artist)
                    }
                }

            }

        }

        if (BuildConfig.DEV){
            baseActivity.getActionBarView()?.setupColorToollbar(R.color.colorPrimaryDev)
            baseActivity.getActionBarView()?.showRightButton(true)
            if(Paper.book().exist("switch")) {
                baseActivity.getActionBarView()?.setupRightButton(layoutInflater.inflate(R.layout.ab_switch, null))
                Paper.book().delete("switch")
            }

            if (baseActivity.netIsConnected()){
                baseActivity.getActionBarView()?.getRightButton()?.isChecked = true

            }else{
                if(artist.isNullOrEmpty()){
                    showErrorSnack(getString(R.string.error_no_intenet_connection))
                }else{
                    initTopArtistAdapter(artist)
                }
                baseActivity.getActionBarView()?.getRightButton()?.isChecked = false
            }
            baseActivity.getActionBarView()?.getRightButton()?.setOnCheckedChangeListener(
                { buttonView, isChecked ->
                    if(!buttonView.isPressed()) {
                        return@setOnCheckedChangeListener
                    }
                    if (isChecked) {
                        setMobileDataEnabled(baseActivity, baseContext, true)

                    } else {
                        setMobileDataEnabled(baseActivity, baseContext, false)
                    }
                })
        }else{
            baseActivity.getActionBarView()?.setupColorToollbar(R.color.colorPrimaryProd)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 545) {
          if (baseActivity.netIsConnected()){
              baseActivity.getActionBarView()?.getRightButton()?.isChecked = true
              getListTopArtist(countryArtist[0])
          }else{
              baseActivity.getActionBarView()?.getRightButton()?.isChecked = false
          }
        }
    }

}