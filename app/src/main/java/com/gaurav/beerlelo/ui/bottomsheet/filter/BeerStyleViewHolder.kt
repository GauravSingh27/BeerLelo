package com.gaurav.beerlelo.ui.bottomsheet.filter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.gaurav.beerlelo.ui.model.BeerStyle
import kotlinx.android.synthetic.main.item_beer_style.view.*

class BeerStyleViewHolder(
        itemView: View,
        private val beerStyleItemClickListener: BeerStyleItemClickListener)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
        itemView.cb_item_beer_style.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        if (v.id == itemView.cb_item_beer_style.id) {
            beerStyleItemClickListener.onItemClick(adapterPosition, itemView.cb_item_beer_style.isChecked)
        }
    }

    fun bindData(beerStyle: BeerStyle?) {

        itemView.cb_item_beer_style.text = beerStyle?.style
        itemView.cb_item_beer_style.isChecked = beerStyle?.isSelected == true
    }

    interface BeerStyleItemClickListener {
        fun onItemClick(position: Int, isSelected: Boolean)
    }
}