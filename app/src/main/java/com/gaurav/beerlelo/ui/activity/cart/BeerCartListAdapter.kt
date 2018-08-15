package com.gaurav.beerlelo.ui.activity.cart

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.ui.model.Beer

internal class BeerCartListAdapter : RecyclerView.Adapter<BeerCartViewHolder>() {


    var beerList: List<Beer>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerCartViewHolder {

        val view = LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.item_beer,
                        parent,
                        false)

        return BeerCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerCartViewHolder, position: Int) {

        holder.bindData(beerList?.get(position))
    }

    override fun getItemCount(): Int {
        return beerList?.size ?: 0
    }
}