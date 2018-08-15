package com.gaurav.beerlelo.ui.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Ignore

class BeerStyle(@ColumnInfo(name = "style")
                val style: String) {

    @Ignore
    var isSelected: Boolean = false
}