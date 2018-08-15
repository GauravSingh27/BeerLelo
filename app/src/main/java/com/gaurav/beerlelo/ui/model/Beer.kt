package com.gaurav.beerlelo.ui.model

import android.arch.persistence.room.ColumnInfo
import com.gaurav.beerlelo.database.model.DBBeer


internal class Beer(

        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "alcoholContent")
        val alcoholContent: Float,

        @ColumnInfo(name = "bitterUnit")
        val bitterUnit: String,

        @ColumnInfo(name = "style")
        val style: String,

        @ColumnInfo(name = "ounces")
        val ounces: Float,
        @ColumnInfo(name = "count")
        var count: Int = 0) {


}


internal fun Beer.convertToDBBeer(): DBBeer {


    return DBBeer(
            id,
            name,
            alcoholContent,
            bitterUnit,
            style,
            ounces,
            count)
}