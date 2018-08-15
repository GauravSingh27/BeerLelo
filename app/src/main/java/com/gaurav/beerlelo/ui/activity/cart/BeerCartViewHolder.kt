package com.gaurav.beerlelo.ui.activity.cart

import android.support.v7.widget.RecyclerView
import android.view.View
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.ui.model.Beer
import kotlinx.android.synthetic.main.item_beer.view.*

internal class BeerCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(beer: Beer?) {

        itemView.tv_item_beer_name.text = beer?.name

        itemView.tv_beer_style.text = beer?.style

        itemView.tv_beer_alcohol_content.text =
                itemView.resources.getString(R.string.alcohol_content, beer?.alcoholContent)

        itemView.iv_item_beer_icon.setImageResource(randomBeerIconProvider())

        itemView.btn_item_beer_add.text = beer?.count.toString()


    }

    private fun randomBeerIconProvider() =
            itemView.resources.obtainTypedArray(R.array.beer_icons)
                    .getResourceId(adapterPosition % 4, 0)
}