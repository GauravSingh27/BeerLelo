package com.gaurav.beerlelo.network.server

internal interface Server {

    val baseUrl: String

    fun <Service> buildApi(service: Class<Service>): Service
}