package com.gaurav.beerlelo.network.mapper

import com.gaurav.beerlelo.appcore.Mapper
import com.gaurav.beerlelo.database.model.DBBeer
import com.gaurav.beerlelo.network.model.NWBeer
import com.gaurav.beerlelo.network.model.convertToBeer
import com.gaurav.beerlelo.ui.model.convertToDBBeer

internal class BeerListMapper : Mapper<List<NWBeer>, List<DBBeer>> {

    override fun map(inParam: List<NWBeer>): List<DBBeer> {

        val beerList = mutableListOf<DBBeer>()


        inParam.forEach({ beerList.add(it.convertToBeer().convertToDBBeer()) })

        return beerList
    }
}