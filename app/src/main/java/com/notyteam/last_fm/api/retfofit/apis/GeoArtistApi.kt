package com.notyteam.last_fm.api.retfofit.apis

import android.content.Context
import com.notyteam.last_fm.BuildConfig
import com.notyteam.last_fm.api.responses.artist.TopArtistResponse
import com.notyteam.last_fm.api.retfofit.BaseRepository
import com.notyteam.last_fm.api.retfofit.api_interface.GeoArtistApiInterface
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import com.notyteam.last_fm.utils.Result

class GeoArtistApi @Inject constructor(
        context: Context,
        retrofit: Retrofit,
        private var getTopArtist: GeoArtistApiInterface
): BaseRepository(context, retrofit) {


    fun getTopArtist(country:String, onResult: (Result<TopArtistResponse>) -> Unit): Single<TopArtistResponse> {
            return initRequestSingleForResult(getTopArtist.getTopArtist(country,BuildConfig.API_KEY), onResult)
    }

}
