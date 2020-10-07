package com.notyteam.last_fm.api.responses.artist


import com.google.gson.annotations.SerializedName
import com.notyteam.last_fm.api.responses.BaseResponse
import noty_team.com.masterovik.ui.adapters.items_adapter.ArtistItemLocal
import javax.annotation.Generated


@Generated("com.robohorse.robopojogenerator")
data class TopArtistResponse(
    @field:SerializedName("errors")
    val error: String? = null,

    @field:SerializedName("topartists")
    val topArtists: ListArtistResponce? = null
)

data class ListArtistResponce(
    @field:SerializedName("artist")
    val artist: ArrayList<ArtistItem?>? = null
): BaseResponse() {

    fun map(): ArrayList<ArtistItemLocal> {
        return artist?.flatMap {

            arrayListOf(
                ArtistItemLocal(
                    name = it?.name ?: "",
                    listeners = it?.listeners ?: "",
                    mbid = it?.mbid ?: "",
                    image = it?.listImages?: null
                )
            )


        } as ArrayList
    }

}


