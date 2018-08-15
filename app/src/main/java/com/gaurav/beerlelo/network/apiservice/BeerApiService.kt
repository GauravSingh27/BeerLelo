package com.gaurav.beerlelo.network.apiservice

import com.gaurav.beerlelo.network.model.NWBeer
import io.reactivex.Single
import retrofit2.http.GET

internal interface BeerApiService {

    @GET("/beercraft")
    fun fetch(): Single<List<NWBeer>>
}