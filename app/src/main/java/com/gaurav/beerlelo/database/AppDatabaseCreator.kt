package com.gaurav.beerlelo.database

import android.arch.persistence.room.Room
import com.gaurav.beerlelo.BeerLeloApplication
import com.gaurav.beerlelo.appcore.Creator

internal class AppDatabaseCreator : Creator<AppDatabase> {


    override fun create(): AppDatabase {

        return DatabaseProvider.instance
    }

    private object DatabaseProvider {
        val dbName: String = "BeerLelo"

        val instance: AppDatabase =
                Room.databaseBuilder(
                        BeerLeloApplication.instance.applicationContext,
                        AppDatabase::class.java,
                        dbName).allowMainThreadQueries()
                        .build()
    }
}