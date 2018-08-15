package com.gaurav.beerlelo.appcore

internal interface Creator<T> {

    fun create(): T
}