package com.notyteam.last_fm.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.notyteam.last_fm.api.responses.artist.TopAlbumsResponse
import com.notyteam.last_fm.api.responses.artist.TopArtistResponse
import com.notyteam.last_fm.base.mvvm.BaseViewModel
import javax.inject.Inject
import com.notyteam.last_fm.utils.Result

class AlbumsViewModel @Inject constructor(app: Application) : BaseViewModel(app) {

    fun getTopAlbums(artist:String): LiveData<Result<TopAlbumsResponse>> {
        val livaData = MutableLiveData<Result<TopAlbumsResponse>>()

        api.topAlbumsApi.getTopAlbums(artist ,{
            livaData.postValue(it)
        }).call()

        return livaData
    }

    override fun onClear() {}

}
