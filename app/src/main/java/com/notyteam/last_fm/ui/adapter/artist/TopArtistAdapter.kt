package com.notyteam.last_fm.ui.adapter.artist

import android.content.Context
import android.view.View
import com.notyteam.last_fm.R
import com.notyteam.last_fm.ui.adapter.BaseAdapter
import com.notyteam.last_fm.utils.loadImage
import kotlinx.android.synthetic.main.item_artist.*
import noty_team.com.masterovik.ui.adapters.items_adapter.ArtistItemLocal
import java.lang.Exception

class TopArtistAdapter(val context: Context, list: ArrayList<ArtistItemLocal>,
                       val onClick: (item: ArtistItemLocal) -> Unit) :
        BaseAdapter<ArtistItemLocal, TopArtistAdapter.ViewHolder>(list) {

    override fun createViewHolder(view: View, layoutId: Int) = ViewHolder(view)

    override fun getItemViewType(position: Int) = R.layout.item_artist

    inner class ViewHolder(view: View) : BaseAdapter.BaseViewHolder(view) {

        init {
            try {
                itemView.getClick {
                    onClick(list[adapterPosition])
                }
            } catch (e: Exception) {
            }
        }

        override fun bind(pos: Int) {
            val round = roundOffDecimal(list[pos].listeners.toDouble()/1000000)
            count_listners.text = context.getString(R.string.listeners,round)
            name_artist.text = list[pos].name
            list[pos].image?.filter { it?.size == "large" }?.forEach {
                cover.loadImage(it?.text.toString(),loading_spinner)
            }


        }
    }

    fun sortList(isSorted: Boolean) {
        if (isSorted) {
            list.sortBy {
                it.name.toLowerCase()
            }
        } else {
            list.sortByDescending{
                it.name.toLowerCase()
            }
        }
        notifyDataSetChanged()
    }


}
