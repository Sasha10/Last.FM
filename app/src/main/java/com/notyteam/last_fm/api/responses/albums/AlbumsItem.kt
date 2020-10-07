package com.notyteam.last_fm.api.responses.artist

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class AlbumsItem (
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("playcount")
    val playcount: Int?,
    @field:SerializedName("image")
    val listImages: ArrayList<ImageItem?>? = null
)

