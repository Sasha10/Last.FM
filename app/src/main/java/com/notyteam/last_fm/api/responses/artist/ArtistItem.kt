package com.notyteam.last_fm.api.responses.artist

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class ArtistItem (
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("listeners")
    val listeners: String?,
    @field:SerializedName("mbid")
    val mbid: String?,
    @field:SerializedName("image")
    val listImages: ArrayList<ImageItem?>? = null
)

data class ImageItem(
    @field:SerializedName("#text")
    val text: String?,
    @field:SerializedName("size")
    val size: String?
)
