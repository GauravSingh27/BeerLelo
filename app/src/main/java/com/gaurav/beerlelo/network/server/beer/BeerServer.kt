package com.gaurav.beerlelo.network.server.beer

import com.gaurav.beerlelo.network.server.BaseServer

internal class BeerServer : BaseServer() {

    override val baseUrl: String
        get() = "http://starlord.hackerearth.com"
}