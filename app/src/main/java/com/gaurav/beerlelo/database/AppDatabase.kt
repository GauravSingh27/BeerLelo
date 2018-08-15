package com.gaurav.beerlelo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.gaurav.beerlelo.database.dao.BeerDao
import com.gaurav.beerlelo.database.model.DBBeer

@Database(
        entities = [(DBBeer::class)],
        version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
}