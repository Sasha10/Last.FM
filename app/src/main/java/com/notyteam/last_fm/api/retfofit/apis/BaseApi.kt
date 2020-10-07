package com.notyteam.last_fm.api.retfofit.apis

import android.content.Context
import com.notyteam.last_fm.App
import javax.inject.Inject

class BaseApi @Inject constructor(val context: Context) {

    @Inject
    lateinit var geoArtistApi: GeoArtistApi

    @Inject
    lateinit var topAlbumsApi: TopAlbumApi

    init {
        App[context].appComponent.inject(this)
    }

}
