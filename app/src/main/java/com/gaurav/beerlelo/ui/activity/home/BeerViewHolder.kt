package com.gaurav.beerlelo.ui.activity.home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.ui.model.Beer
import kotlinx.android.synthetic.main.item_beer.view.*

internal class BeerViewHolder(
        itemView: View,
        private val beerListAdapterEventListener: BeerListAdapter.BeerListAdapterEventListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var beer: Beer? = null


    init {
        itemView.btn_item_beer_add.setOnClickListener(this)
    }

    fun bindData(beer: Beer?) {

        this.beer = beer

        itemView.tv_item_beer_name.text = beer?.name

        itemView.tv_beer_style.text = beer?.style

        itemView.tv_beer_alcohol_content.text =
                itemView.resources.getString(R.string.alcohol_content, beer?.alcoholContent)

        itemView.iv_item_beer_icon.setImageResource(randomBeerIconProvider())

        val btnText: String = if (beer?.count == 0) itemView.context.getString(R.string.add) else beer?.count.toString()
        itemView.btn_item_beer_add.text = btnText

    }

    override fun onClick(v: View?) {
        beerListAdapterEventListener.addToCart(beer!!.id, beer!!.count)
    }

    private fun randomBeerIconProvider() =
            itemView.resources.obtainTypedArray(R.array.beer_icons)
                    .getResourceId(adapterPosition % 4, 0)
}