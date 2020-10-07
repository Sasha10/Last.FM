package com.notyteam.last_fm.ui.adapter.albums

import android.content.Context
import android.view.View
import com.notyteam.last_fm.R
import com.notyteam.last_fm.ui.adapter.BaseAdapter
import com.notyteam.last_fm.utils.loadImage
import kotlinx.android.synthetic.main.item_albums.*
import noty_team.com.masterovik.ui.adapters.items_adapter.AlbumsItemLocal

class TopAlbumsAdapter(val context: Context, list: ArrayList<AlbumsItemLocal>,
                       val onClick: (item: AlbumsItemLocal) -> Unit) :
    BaseAdapter<AlbumsItemLocal, TopAlbumsAdapter.ViewHolder>(list) {

    override fun createViewHolder(view: View, layoutId: Int) = ViewHolder(view)

    override fun getItemViewType(position: Int) = R.layout.item_albums

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
            val round = roundOffDecimal(list[pos].playcount.toDouble()/1000000)
            playcount.text = context.getString(R.string.scrobbles,round)
            title_albums.text = list[pos].name

            list[pos].image?.filter { it?.size == "large" }?.forEach {
                cover.loadImage(it?.text.toString(),loading_spinner)
            }
            number_albums.text = (pos+1).toString()

        }

    }

}



