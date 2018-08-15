package com.gaurav.beerlelo.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "DBBeer")

class DBCart(
        @ColumnInfo(name = "id")
        @PrimaryKey
        val id: Int,

        @ColumnInfo(name = "count")
        val name: String)