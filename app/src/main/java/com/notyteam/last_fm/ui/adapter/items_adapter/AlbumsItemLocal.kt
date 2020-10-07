package noty_team.com.masterovik.ui.adapters.items_adapter

import com.notyteam.last_fm.api.responses.artist.ImageItem

data class AlbumsItemLocal(var name: String = "",
                           var playcount: Int = 0,
                           val image: ArrayList<ImageItem?>? = null)