package com.gaurav.beerlelo.repository

import android.arch.lifecycle.LiveData
import android.util.Log
import com.gaurav.beerlelo.database.AppDatabaseCreator
import com.gaurav.beerlelo.database.dao.BeerDao
import com.gaurav.beerlelo.network.apiservice.BeerApiService
import com.gaurav.beerlelo.network.mapper.BeerListMapper
import com.gaurav.beerlelo.network.server.beer.BeerServer
import com.gaurav.beerlelo.ui.bottomsheet.sort.ALCOHOL_CONTENT_ASC
import com.gaurav.beerlelo.ui.bottomsheet.sort.ALCOHOL_CONTENT_DESC
import com.gaurav.beerlelo.ui.bottomsheet.sort.NAME
import com.gaurav.beerlelo.ui.model.Beer
import io.reactivex.schedulers.Schedulers

internal class BeerRepository : Repository {

    override val tag = "BeerRepository"

    private val beerDao: BeerDao by lazy { AppDatabaseCreator().create().beerDao() }
    private val beerApiService: BeerApiService by lazy { BeerServer().buildApi(BeerApiService::class.java) }

    init {
        refreshBeers()
    }

    fun fetchBeerStyles() = beerDao.queryAllBeerStyle()

    fun updateCount(id: Int?, count: Int) = beerDao.updateCount(id!!, count)


    fun searchBeer(search: String = "", sortBy: Int = NAME, beerStyles: List<String>?): LiveData<List<Beer>> {

        return when (sortBy) {
            ALCOHOL_CONTENT_ASC -> beerDao.queryOrderByAlcoholContentAsc(search, beerStyles)
            ALCOHOL_CONTENT_DESC -> beerDao.queryOrderByAlcoholContentDesc(search, beerStyles)
            else -> beerDao.queryOrderByName(search, beerStyles)
        }
    }

    private fun refreshBeers() {

        beerApiService
                .fetch()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { beerDao.insertAll(BeerListMapper().map(it)) },
                        { Log.e(tag + "refresh", it?.localizedMessage) })
    }

}