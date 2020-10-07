package com.notyteam.last_fm.api.retfofit.apis

import android.content.Context
import com.notyteam.last_fm.BuildConfig
import com.notyteam.last_fm.api.responses.artist.TopAlbumsResponse
import com.notyteam.last_fm.api.responses.artist.TopArtistResponse
import com.notyteam.last_fm.api.retfofit.BaseRepository
import com.notyteam.last_fm.api.retfofit.api_interface.GeoArtistApiInterface
import com.notyteam.last_fm.api.retfofit.api_interface.TopAlbumsApiInterface
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import com.notyteam.last_fm.utils.Result

class TopAlbumApi @Inject constructor(
        context: Context,
        retrofit: Retrofit,
        private var getTopAlbums: TopAlbumsApiInterface
): BaseRepository(context, retrofit) {


    fun getTopAlbums(artist:String, onResult: (Result<TopAlbumsResponse>) -> Unit): Single<TopAlbumsResponse> {
            return initRequestSingleForResult(getTopAlbums.getTopAlbums(artist,BuildConfig.API_KEY), onResult)
    }

}
