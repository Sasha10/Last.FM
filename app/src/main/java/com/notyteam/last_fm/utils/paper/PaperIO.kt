package com.noty_team.damfastore.utils.paper

import com.notyteam.last_fm.api.responses.artist.ListAlbumsResponce
import io.paperdb.Paper
import noty_team.com.masterovik.ui.adapters.items_adapter.AlbumsItemLocal
import noty_team.com.masterovik.ui.adapters.items_adapter.ArtistItemLocal


class PaperIO {
    companion object {
        private const val LIST_ARTIST = "list_artist"
        private const val LIST_ALBUMS = "list_albums"
        private const val DATA_ALBUMS = "data_albums"


        var artist: ArrayList<ArtistItemLocal>
            get() {
                return if (Paper.book().exist(LIST_ARTIST))
                    Paper.book().read(LIST_ARTIST)
                else arrayListOf()
            }
            set(value) {
                Paper.book().write(LIST_ARTIST, value)
            }


        fun getAlbums(id:String): ArrayList<AlbumsItemLocal>? {
            return if (Paper.book().exist(LIST_ALBUMS+id))
                Paper.book().read(LIST_ALBUMS+id)
            else null
        }

        fun setAlbums(id:String,list:ArrayList<AlbumsItemLocal>?){
            Paper.book().write(LIST_ALBUMS+id, list)
        }

        fun getDataAlbums(id:String): ListAlbumsResponce? {
            return if (Paper.book().exist(DATA_ALBUMS+id))
                Paper.book().read(DATA_ALBUMS+id)
            else null
        }

        fun setDataAlbums(id:String,list:ListAlbumsResponce?){
            Paper.book().write(DATA_ALBUMS+id, list)
        }

    }


}



