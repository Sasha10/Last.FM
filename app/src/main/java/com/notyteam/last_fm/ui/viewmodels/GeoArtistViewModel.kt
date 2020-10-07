package com.notyteam.last_fm.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.notyteam.last_fm.api.responses.artist.TopArtistResponse
import com.notyteam.last_fm.base.mvvm.BaseViewModel
import javax.inject.Inject
import com.notyteam.last_fm.utils.Result

class GeoArtistViewModel @Inject constructor(app: Application) : BaseViewModel(app) {

    fun getTopArtist(country:String): LiveData<Result<TopArtistResponse>> {
        val livaData = MutableLiveData<Result<TopArtistResponse>>()

        api.geoArtistApi.getTopArtist(country ,{
            livaData.postValue(it)
        }).call()

        return livaData
    }

    override fun onClear() {}

}
