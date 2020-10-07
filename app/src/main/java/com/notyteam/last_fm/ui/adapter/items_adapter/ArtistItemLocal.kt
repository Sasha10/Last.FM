package noty_team.com.masterovik.ui.adapters.items_adapter

import com.notyteam.last_fm.api.responses.artist.ImageItem

data class ArtistItemLocal(var name: String = "",
                           var listeners: String = "",
                           var mbid: String = "",
                           val image: ArrayList<ImageItem?>? = null)