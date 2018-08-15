package com.gaurav.beerlelo.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "DBBeer")
internal class DBBeer(

        @ColumnInfo(name = "id")
        @PrimaryKey
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
        var count: Int = 0)