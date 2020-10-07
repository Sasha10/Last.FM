package com.notyteam.last_fm.ui.fragments.albums

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.noty_team.damfastore.utils.paper.PaperIO
import com.noty_team.damfastore.utils.paper.PaperIO.Companion.setAlbums
import com.noty_team.damfastore.utils.paper.PaperIO.Companion.setDataAlbums
import com.notyteam.last_fm.R
import com.notyteam.last_fm.api.responses.artist.ListAlbumsResponce
import com.notyteam.last_fm.base.BaseFragment
import com.notyteam.last_fm.ui.adapter.albums.TopAlbumsAdapter
import com.notyteam.last_fm.ui.viewmodels.AlbumsViewModel
import com.notyteam.last_fm.utils.Failure
import com.notyteam.last_fm.utils.Success
import com.notyteam.last_fm.utils.injectViewModel
import com.notyteam.last_fm.utils.loadImage
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_top_albums.*
import noty_team.com.masterovik.ui.adapters.items_adapter.AlbumsItemLocal

class TopAlbumsFragment : BaseFragment<AlbumsViewModel>() {


    private lateinit var topAlbumsAdapter:  TopAlbumsAdapter

    companion object {

        const val ITEM = "item"
        const val NAME = "name"
        const val MBID = "mbid"
        fun newInstance(item: String?,name: String?,mbid: String?): TopAlbumsFragment {
            return TopAlbumsFragment().apply {
                arguments = Bundle().apply {
                    putString(ITEM, item)
                    putString(NAME,name)
                    putString(MBID,mbid)
                }
            }
        }

        fun getMbid(args: Bundle?): String {
            val mBid = args?.getString(MBID) ?: ""
            return mBid
        }

        fun getNameArtist(args: Bundle?): String {
            val nameArtist = args?.getString(NAME) ?: ""
            return nameArtist
        }
        fun getImageArtistUrl(args: Bundle?): String {
            val imageArtistUrl = args?.getString(ITEM) ?: ""
            return imageArtistUrl
        }
    }



        override fun layout() = R.layout.fragment_top_albums

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): AlbumsViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

        initToolbar()
        if (isFirstInit){

            if (baseActivity.netIsConnected()){
                getTopAlbums()
            }else{
                if(PaperIO.getAlbums(getMbid(arguments)).isNullOrEmpty()){
                    showErrorSnack(getString(R.string.not_item_db))
                    baseActivity.supportFragmentManager.popBackStack()
                 }else{
                    initViews(PaperIO.getDataAlbums(getMbid(arguments)))
                    initTopAlbumsAdapter(PaperIO.getAlbums(getMbid(arguments))!!)
                }
            }
        }
    }
    private fun getTopAlbums() {
        isShowLoadingDialog(true)
        listenUpdates()
    }

    private fun listenUpdates() {
        viewModel.getTopAlbums(getNameArtist(arguments)).observe(this) { response ->
            isShowLoadingDialog(false)
            when (response) {
                is Success -> {
                    setAlbums(getMbid(arguments),response.value.topAlbums!!.map())
                    setDataAlbums(getMbid(arguments),response.value.topAlbums)
                    initViews(response.value.topAlbums)
                    initTopAlbumsAdapter(response.value.topAlbums!!.map())
                }
                is Failure -> {
                    showErrorSnack(response.errorMessage)
                }
            }
        }
    }
    private fun initTopAlbumsAdapter(list: ArrayList<AlbumsItemLocal>){

        recycler_tracks.layoutManager = LinearLayoutManager(baseActivity)
        topAlbumsAdapter = TopAlbumsAdapter(baseContext, list) {
            //fragmentManager.addFragment(AudioPlayerFragment.newInstance(getProgramsData(arguments),it))
        }
        recycler_tracks.adapter = topAlbumsAdapter

    }


    private fun initToolbar() {
        baseActivity.getActionBarView()?.showRightButton(false)
        baseActivity.getActionBarView()?.showActionBar(true)
        baseActivity.getActionBarView()?.setupCenterText(getNameArtist(arguments))
        baseActivity.getActionBarView()?.showLeftButton(true)
        baseActivity.getActionBarView()?.showSpinnerCountry(false)
        baseActivity.getActionBarView()?.setupLeftButton(layoutInflater.inflate(R.layout.ab_back, null))
        baseActivity.getActionBarView()?.getLeftButton()?.getClick {
            onBackPressed()
        }

    }
    private fun initViews(topAlbums: ListAlbumsResponce?) {

        title.text = getNameArtist(arguments)
        size_programs.text = baseContext.resources.getQuantityString(
            R.plurals.albums_plurals, topAlbums?.map()!!.size, topAlbums?.map()!!.size
        )
        cover.loadImage(getImageArtistUrl(arguments), loading_spinner)

    }
}