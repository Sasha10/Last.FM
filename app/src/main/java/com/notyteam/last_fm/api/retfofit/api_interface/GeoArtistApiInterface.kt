package com.notyteam.last_fm.api.retfofit.api_interface


import com.notyteam.last_fm.api.responses.artist.TopArtistResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GeoArtistApiInterface {

    @GET("?method=geo.gettopartists&format=json")
    fun getTopArtist(@Query("country") country:String,@Query("api_key") api_key:String): Single<TopArtistResponse>

}