package com.gaurav.beerlelo.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.gaurav.beerlelo.database.model.DBBeer
import com.gaurav.beerlelo.ui.model.Beer
import com.gaurav.beerlelo.ui.model.BeerStyle

@Dao
internal interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(beerList: List<DBBeer>)

    @Query("SELECT * FROM DBBeer WHERE name LIKE '%' || :search || '%' AND style IN (:beerStyles) ORDER BY name ASC")
    fun queryOrderByName(search: String, beerStyles: List<String>?): LiveData<List<Beer>>

    @Query("SELECT * FROM DBBeer WHERE name LIKE '%' || :search || '%' AND style IN (:beerStyles) ORDER BY alcoholContent ASC")
    fun queryOrderByAlcoholContentAsc(search: String, beerStyles: List<String>?): LiveData<List<Beer>>

    @Query("SELECT * FROM DBBeer WHERE name LIKE '%' || :search || '%' AND style IN (:beerStyles) ORDER BY alcoholContent DESC")
    fun queryOrderByAlcoholContentDesc(search: String, beerStyles: List<String>?): LiveData<List<Beer>>

    @Query("SELECT DISTINCT style FROM DBBeer")
    fun queryAllBeerStyle(): LiveData<List<BeerStyle>>

    @Query("UPDATE DBBeer SET count = :count WHERE id = :beerId")
    fun updateCount(beerId: Int, count: Int)
}