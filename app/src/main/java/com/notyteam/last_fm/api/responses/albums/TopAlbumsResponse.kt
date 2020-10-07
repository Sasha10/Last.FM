package com.notyteam.last_fm.api.responses.artist


import com.google.gson.annotations.SerializedName
import com.notyteam.last_fm.api.responses.BaseResponse
import noty_team.com.masterovik.ui.adapters.items_adapter.AlbumsItemLocal
import noty_team.com.masterovik.ui.adapters.items_adapter.ArtistItemLocal
import javax.annotation.Generated


@Generated("com.robohorse.robopojogenerator")
data class TopAlbumsResponse(
    @field:SerializedName("errors")
    val error: String? = null,

    @field:SerializedName("topalbums")
    val topAlbums: ListAlbumsResponce? = null
)

data class ListAlbumsResponce(
    @field:SerializedName("album")
    val album: ArrayList<AlbumsItem?>? = null
): BaseResponse() {

    fun map(): ArrayList<AlbumsItemLocal> {
        return album?.flatMap {

            arrayListOf(
                AlbumsItemLocal(
                    name = it?.name ?: "",
                    playcount = it?.playcount ?: 0,
                    image = it?.listImages?: null
                )
            )


        } as ArrayList
    }

}


