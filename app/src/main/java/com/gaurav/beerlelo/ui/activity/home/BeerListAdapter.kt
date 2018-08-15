package com.gaurav.beerlelo.ui.activity.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.ui.model.Beer

internal class BeerListAdapter(private val beerListAdapterEventListener: BeerListAdapterEventListener) :
        RecyclerView.Adapter<BeerViewHolder>() {


    var beerList: List<Beer>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {

        val view = LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.item_beer,
                        parent,
                        false)

        return BeerViewHolder(view, beerListAdapterEventListener)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {

        holder.bindData(beerList?.get(position))
    }

    override fun getItemCount(): Int {
        return beerList?.size ?: 0
    }

    interface BeerListAdapterEventListener {
        fun addToCart(beerId: Int?, quantity: Int)
    }
}