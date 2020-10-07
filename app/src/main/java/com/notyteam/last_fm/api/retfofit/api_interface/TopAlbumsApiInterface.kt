package com.notyteam.last_fm.api.retfofit.api_interface


import com.notyteam.last_fm.api.responses.artist.TopAlbumsResponse
import com.notyteam.last_fm.api.responses.artist.TopArtistResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface TopAlbumsApiInterface {

    @GET("?method=artist.gettopalbums&format=json")
    fun getTopAlbums(@Query("artist") artist:String,@Query("api_key") api_key:String): Single<TopAlbumsResponse>

}