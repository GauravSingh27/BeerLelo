package com.gaurav.beerlelo.ui.bottomsheet.filter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.ui.model.BeerStyle

class BeerStyleListAdapter(
        private val beerStyleListAdapterEventListener: BeerStyleListAdapterEventListener) :
        RecyclerView.Adapter<BeerStyleViewHolder>(),
        BeerStyleViewHolder.BeerStyleItemClickListener {

    var beerStyles: List<BeerStyle>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerStyleViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.item_beer_style,
                        parent,
                        false)

        return BeerStyleViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: BeerStyleViewHolder, position: Int) {

        holder.bindData(beerStyles?.get(position))
    }

    override fun getItemCount(): Int {
        return beerStyles?.size ?: 0
    }

    override fun onItemClick(position: Int, isSelected: Boolean) {
        beerStyles?.get(position)?.isSelected = isSelected

        beerStyleListAdapterEventListener.atLeastOneItemSelected(isSelected || isAtLeastOneItemSelected())

    }

    interface BeerStyleListAdapterEventListener {
        fun atLeastOneItemSelected(selected: Boolean)
    }

    private fun isAtLeastOneItemSelected(): Boolean {
        return beerStyles?.find { it.isSelected }?.isSelected ?: false
    }
}
