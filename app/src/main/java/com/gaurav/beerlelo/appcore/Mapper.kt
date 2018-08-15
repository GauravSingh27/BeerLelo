package com.gaurav.beerlelo.appcore

internal interface Mapper<InParam, OutParam> {

    fun map(inParam: InParam): OutParam
}